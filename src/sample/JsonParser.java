
package sample;

import sample.json.JSONArray;
import sample.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by arup3 on 5/16/2017.
 */
public class JsonParser {

    JSONObject jsonObject;

    public ArrayList<RepoModel> parseJson(String mJson){
        ArrayList<RepoModel> modelList = new ArrayList<>();
        jsonObject = new JSONObject(mJson);

        try{
            JSONArray array = jsonObject.getJSONArray("repo");
            for(int i=0;i<array.length();i++){
                RepoModel model = new RepoModel();
                JSONObject obj = array.getJSONObject(i);
                model.setName(obj.getString("name"));
                model.setPermission(obj.getString("permission"));
                model.setDependency(obj.getString("dependency"));
                model.setCodeSnipet(obj.getString("codeSnipet"));
                model.setMinSdk(obj.getString("minSdk"));
                model.setXML(obj.getBoolean("isXml"));
                model.setSearchKey(obj.getString("searchKey"));
                model.setDeprecated(obj.getBoolean("isDepcrecated"));

                modelList.add(model);
            }
        }catch(Exception e){
            System.out.println("error paring the json: "+e.toString());
            return null;
        }
        return modelList;
    }

}
