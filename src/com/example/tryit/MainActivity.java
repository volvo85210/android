package com.example.tryit;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;

public class MainActivity extends Activity {

	public ListView listCats;
	public String catsName[]={"Taipei","Hsinchu","Taoyuan","Taichung", "Tainan"};
	View mView;
	
    
	public List<Map<String, Object>> dt;
	
    public SimpleAdapter myAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView img=(ImageView)findViewById(R.id.ItemImage);
        listCats=(ListView)findViewById(R.id.LV1);
       // mView = LayoutInflater.from(this).inflate(R.layout.activity_main,null);
        dt = new ArrayList<Map<String, Object>>();
        HashMap<String, Object> i=new HashMap<String, Object>();
        i.put("image",R.drawable.x1);
        i.put("title", "            sunny");        
        i.put("text", "              little");
        dt.add(i);
        myAdapter=new SimpleAdapter(getApplicationContext(), 
                dt,R.layout.activity_main, 
        new String[]{"title","text","image"},
        new int[]{R.id.ItemTitle,R.id.ItemText, R.id.ItemImage});
        listCats.setAdapter(myAdapter);
        //listCats.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , catsName));
        
        //listCats.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
        listCats.setOnItemClickListener(new OnItemClickListener() {       
		
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
        	//Log.e("tag", catsName[arg2]);
        	try {
				jumptolayout2(catsName[arg2]);
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
		}
        });
        
    }
    
   
    public void picturechange(){
    	
    }
    
    public void jumptolayout2(String city) throws XmlPullParserException
    {
    	dt = new ArrayList<Map<String, Object>>();
    	setContentView(R.layout.activity2);
 	
        ListView listview2 =(ListView) findViewById(R.id.LV2);
        String result[]={"City: ","today's temp_C "," "," ", " "," "};
        
        
			try {
	    	    String xml="http://www.google.com/ig/api?hl=zh-tw&weather=";
	    	    xml=xml+city;
	    	    URL url = new URL(xml);
	    	    InputStream is;
				is = url.openStream();
				
	    	    StringBuffer strBuf = new StringBuffer();
	    	    //String strNode=null;
	    	    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
	    	    XmlPullParser xpParser = factory.newPullParser();
	    	    
	    	    xpParser.setInput(is,"UTF-8");
	    	    
	    	    int eventType = xpParser.getEventType();
	    	    int x=0;
	            while (eventType != XmlPullParser.END_DOCUMENT) {
	             if(eventType == XmlPullParser.START_DOCUMENT) {
	            	 Log.e("START_DOCUMENT", "Start document");
	                 
	             } else if(eventType == XmlPullParser.START_TAG) {
	            	
	            	 if (xpParser.getName().equals("temp_c")){
	            		// Log.e("END_TAG", xpParser.getAttributeValue(0));
	            		 result[1]=result[1]+xpParser.getAttributeValue(0);
	            		 Log.e("tempc", xpParser.getAttributeValue(0));
	            		 x=2;
	            	 }
	            	 if (xpParser.getName().equals("city")){
	            		
	            		 String strReturn="";   
	            		    try   
	            		    {   
	            		      strReturn = new String(xpParser.getAttributeValue(0).getBytes("big5"), "utf-8");   
	            		    }   
	            		    catch (Exception e)   
	            		    {   
	            		      e.printStackTrace();   
	            		    }
	            		    result[0]=result[0]+strReturn;
	            		 Log.e("city", strReturn);
	            		// i.put("city", strReturn);
	            		 result[0]=result[0]+strReturn;
	            	//	dt.add(i);
	            	 }
	            	 
	            	 if (xpParser.getName().equals("day_of_week")){
	            		// HashMap<String, Object> i=new HashMap<String, Object>();
	            		 
	            		 String strReturn="";   
	            		    try   
	            		    {   
	            		      strReturn = new String(xpParser.getAttributeValue(0).getBytes("big5"), "utf-8");   
	            		    }   
	            		    catch (Exception e)   
	            		    {   
	            		      e.printStackTrace();   
	            		    }
	            		 Log.e("day_of_week", strReturn);
	            		 result[x]=result[x]+strReturn+" ";
	            		 //i.put("week", strReturn);
	            		 
	            	 }
	            	 
	            	 if (xpParser.getName().equals("condition")){
	            //		 Map<String, Object> i = new HashMap<String, Object>();
	            		 String strReturn="";   
	            		    try   
	            		    {   
	            		      strReturn = new String(xpParser.getAttributeValue(0).getBytes("big5"), "utf-8");   
	            		    }   
	            		    catch (Exception e)   
	            		    {   
	            		      e.printStackTrace();   
	            		    }
	            		  result[x]=result[x]+strReturn;  
	            		    /*
	            		    if (strReturn=="多雲時晴"){
	            		    i.put("picture", R.drawable.pic1);
	            		    }else if(strReturn=="雷陣雨"){
	            		    	i.put("picture", R.drawable.pic3);
	            		    }
	            		    */
	            		 Log.e("condition",strReturn);
	            		// dt.add(i);
	            		
	            	 }
	            	 
	             } else if(eventType == XmlPullParser.END_TAG) {
	            	 Log.e("END_TAG", xpParser.getName());
	            	 
	             } else if(eventType == XmlPullParser.TEXT) {
	            	 Log.e("TEXT", xpParser.getText());
	                 
	             }
	             eventType = xpParser.next();
	            }
	            listCats.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , result));
	            listview2.setTextFilterEnabled(true);
/*
	            myAdapter=new SimpleAdapter(getApplicationContext(), 
	                    dt,R.layout.activity2, 
	            new String[]{"title","text","image"},
	            new int[]{R.id.ItemTitle,R.id.ItemText, R.id.ItemImage});
	            listview2.setAdapter(myAdapter);
	            //myAdapter.notifyDataSetChanged();     
	            listview2.setTextFilterEnabled(true);
				*/
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
    	
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
