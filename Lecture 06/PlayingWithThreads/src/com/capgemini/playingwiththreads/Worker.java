package com.capgemini.playingwiththreads;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Environment;
import android.util.Log;

public class Worker {
	private final boolean _useGpsToGetLocation = false;
	
	Context _context;
	public Worker(Context context) {
		_context = context;
	}

	public Location getLocation() {
		Location lastLocation = null;
		
		if (_useGpsToGetLocation)
		{
			LocationManager locationManager = (LocationManager) _context.getSystemService(Context.LOCATION_SERVICE);
			lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		}
		
		if(lastLocation == null)
			lastLocation = createLocationManually();
		
		addDelay();
		return lastLocation;
	}

	public String reverseGeocode(Location location) {
		String addressDescription = null;
		
		try  {
			Geocoder geocoder = new Geocoder(_context);
			List<Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 5);
			if(!addressList.isEmpty())
			{
				Address firstAddress = addressList.get(0);
				
				StringBuilder addressBuilder = new StringBuilder();
				for (int i = 0; i <= firstAddress.getMaxAddressLineIndex(); i++)
				{
					if(i != 0)
						addressBuilder.append(", ");
					addressBuilder.append(firstAddress.getAddressLine(i));
				}
				addressDescription = addressBuilder.toString();
			}
			else {
				addressDescription = reverseGeoCodeWithWebService(location);
			}
		}
		catch (IOException ex) {
			addressDescription = reverseGeoCodeWithWebService(location);
		}
		catch (Exception ex) {
			Log.e("Worker.reverseGeocode", "Error");
		}
		
		addDelay();
		return addressDescription;
	}

	public void save(Location location, String address, String fileName) {
		try {
			File targetDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			if(!targetDir.exists())
				targetDir.mkdirs();
			
			File outFile = new File(targetDir, fileName);
			FileWriter fileWriter = new FileWriter(outFile, true);
			BufferedWriter writer = new BufferedWriter(fileWriter);
			
			String outLine = String.format(Locale.getDefault(), "%s - %f/%f\n", new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(location.getTime()), location.getLatitude(), location.getLongitude());
			writer.write(outLine);
			writer.write(address + "\n");
			
			writer.flush();
			writer.close();
			fileWriter.close();
		}
		catch (Exception ex){
            Log.e("Worker.save", "Error");
        }
		
		addDelay();
	}
	
	// Reverse geocode that lat/lng using the Google Web Service API
    private String reverseGeoCodeWithWebService(Location location){

        String addressDescription = null;
		try {
            // Create the Google Web Service URL to use to retrieve the address for the lat/lng
            String serviceUrl =
                    String.format(Locale.getDefault(), "http://maps.google.com/maps/api/geocode/xml?sensor=false&latlng=%f,%f",
                    location.getLatitude(), location.getLongitude());

            // Create the HTTP Client and make the call
            HttpGet httpGet = new HttpGet(serviceUrl);
            HttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(httpGet);

            // Retrieve the Web Service response and wrap in a Reader
			HttpEntity entity = response.getEntity();
			InputStream stream = entity.getContent();
            InputStreamReader reader = new InputStreamReader(stream);

            // Use the XML Parse to locate the formatted address in the response
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(reader);
            boolean isAddressNode = false;
            int eventType = xpp.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){
                if(eventType == XmlPullParser.START_TAG){
                    String tagName = xpp.getName();
                    if(tagName.equalsIgnoreCase("formatted_address")){
                        // This is the formatted address element so set the flag indicating
                        //  that we need to read the address from the next text element
                        isAddressNode = true;
                    }
                }
                else if (isAddressNode && eventType == XmlPullParser.TEXT){
                    // This is the text element w/in the formatted_address so read it
                    //  then exit because we have what we came for
                    addressDescription = xpp.getText();
                    break;

                }
                eventType = xpp.next();
            }
		} catch (Exception ex) {
            Log.e("Worker.reverseGeoCodeWithWebService", "Error");
		}

        return addressDescription;
    }
	
	private Location createLocationManually() {
		Location lastLocation = new Location("Hiof");
        Date now = new Date();
        lastLocation.setTime(now.getTime());
        lastLocation.setLatitude(59.128229);
        lastLocation.setLongitude(11.352860);

        return lastLocation;
	}
	
	private void addDelay() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
