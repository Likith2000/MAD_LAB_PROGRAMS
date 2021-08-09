package in.bmsit.sixthsem.parse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static java.nio.charset.StandardCharsets.UTF_8;

public class MainActivity extends AppCompatActivity {
    Button bJSON;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        bJSON = findViewById(R.id.bJSON);
        tv = findViewById(R.id.outputDisplay);
        bJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String json;
                StringBuilder sb = new StringBuilder();
                try {
                    InputStream is = getAssets().open("cityParser.json");
                    int size = is.available()+1;
                    byte[] buff = new byte[size];
                    is.read(buff);
                    json = new String(buff,UTF_8);
                    JSONArray ja = new JSONArray(json);
                    sb.append("JSON Data\n----------\n");
                    for (int i = 0; i < ja.length(); ++i){
                        JSONObject jo = ja.getJSONObject(i);
                        sb.append("City Name:").append(jo.getString("City_Name")).append("\n");
                        sb.append("Latitude:").append(jo.getString("Latitude")).append("\n");
                        sb.append("Longitude:").append(jo.getString("Longitude")).append("\n");
                        sb.append("Temperature:").append(jo.getString("Temperature")).append("\n");
                        sb.append("Humidity:").append(jo.getString("Humidity")).append("\n\n");
                        tv.setText(sb.toString());
//                        is.close();
                    }
                } catch (IOException | JSONException e) {
                    tv.setText("File not found");
                    e.printStackTrace();
                }
            }
        });
    }

    public void parseXML(View V){
        String xml;
        try {
            InputStream is = getAssets().open("cityParserXML.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document d = db.parse(is);
            StringBuilder sb = new StringBuilder();
            sb.append("XML Data\n----------\n");
            NodeList nl = d.getElementsByTagName("City");
            for (int i= 0; i < nl.getLength() ; ++i){
                Node n = nl.item(i);
                if(n.getNodeType() == Node.ELEMENT_NODE){
                    Element l = (Element)n;
                    sb.append("City Name:").append(getValue("Name",l)).append("\n");
                    sb.append("Latitude:").append(getValue("Latitude",l)).append("\n");
                    sb.append("Longitude:").append(getValue("Longitude",l)).append("\n");
                    sb.append("Temperature:").append(getValue("Temperature",l)).append("\n");
                    sb.append("Humidity:").append(getValue("Humidity",l)).append("\n\n");
                }
            }
            tv.setText(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getValue(String t, Element e){
        return e.getElementsByTagName(t).item(0).getChildNodes().item(0).getNodeValue();
    }

}