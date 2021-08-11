package in.bmsit.sixthsem.parse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static java.nio.charset.StandardCharsets.UTF_8;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tv = findViewById(R.id.outputDisplay);
    }

    public void parsexml(View V)
    {
        try {
            InputStream is = getAssets().open("cityParserXML.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(is);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("XML DATA");
            stringBuilder.append("\n---------");
            NodeList nodeList = document.getElementsByTagName("City");
            for (int i = 0; i < nodeList.getLength(); i++)
            {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    stringBuilder.append("\nName: ").append(getValue("Name", element));
                    stringBuilder.append("\nLatitude: ").append(getValue("Latitude", element));
                    stringBuilder.append("\nLongitude: ").append(getValue("Longitude", element));
                    stringBuilder.append("\nTemperature: ").append(getValue("Temperature", element));
                    stringBuilder.append("\nHumidity: ").append(getValue("Humidity", element));
                    stringBuilder.append("\n----------");
                }}
            tv.setText(stringBuilder.toString());
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MainActivity.this,"Error Parsing XML",Toast.LENGTH_LONG).show();
        }
    }

    public void parsejson(View V){
        String json;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream is = getAssets().open("cityParser.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);
            stringBuilder.append("JSON DATA");
            stringBuilder.append("\n--------");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                stringBuilder.append("\nName: ").append(jsonObject.getString("City_Name"));
                stringBuilder.append("\nLatitude: ").append(jsonObject.getString("Latitude"));
                stringBuilder.append("\nLongitude: ").append(jsonObject.getString("Longitude"));
                stringBuilder.append("\nTemperature: ").append(jsonObject.getString("Temperature"));
                stringBuilder.append("\nHumidity: ").append(jsonObject.getString("Humidity"));
                stringBuilder.append("\n----------");
            }
            tv.setText(stringBuilder.toString());
            is.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MainActivity.this,"Error in reading",Toast.LENGTH_LONG).show();
        }
    }
    private String getValue(String tag, Element element)
    {
        return element.getElementsByTagName(tag).item(0).getChildNodes().item(0).getNodeValue();
    }
}
