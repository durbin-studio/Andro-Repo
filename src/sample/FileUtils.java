package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arup3 on 5/16/2017.
 */
public class FileUtils {

    String jsonFile = "repo.json";

    public String readFile(){

        if(checkFileExistance()){
            BufferedReader br = null;
            String jsonString= null;
            try {
                br= new BufferedReader(new FileReader(jsonFile));
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    line = br.readLine();
                }
                jsonString = sb.toString();
            }catch(Exception e){
                System.out.println("error reading file:  "+e.toString());
            } finally {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("error reading file:  "+e.toString());
                }
                System.out.println("json string: "+jsonString);
                return jsonString;
            }
        }else {
            return null;
        }
    }



    public String createJosn(List<RepoModel> modelList){
        String mJson = "{" +
                "\"repo\":[ " ;
        for(int i=0;i<modelList.size();i++){
            if(i!=0 && i<modelList.size()){
                mJson+=",";
            }
            mJson +="{\"name\": \""+quoteHandler(modelList.get(i).getName())+"\""+
                    ","+
                    "\"permission\": \""+quoteHandler(modelList.get(i).getPermission())+"\""+
                    ","+
                    "\"dependency\": \""+quoteHandler(modelList.get(i).getDependency())+"\""+
                    ","+
                    "\"codeSnipet\": \""+quoteHandler(modelList.get(i).getCodeSnipet())+"\""+
                    ","+
                    "\"minSdk\": \""+quoteHandler(modelList.get(i).getMinSdk())+"\""+
                    ","+
                    "\"isXml\": "+modelList.get(i).isXML()+
                    ","+
                    "\"searchKey\": \""+quoteHandler(modelList.get(i).getSearchKey())+"\""+
                    ","+
                    "\"isDepcrecated\": "+modelList.get(i).isDeprecated()+"}";
        }
        mJson+="]" +
                "}";

        System.out.println("created json: "+mJson);
        return mJson;
    }

    public void rewriteJson (String mJson) {
        BufferedWriter bw = null;
        try {
            System.out.println("started file writing");
            checkFileExistance();
            bw = new BufferedWriter(new FileWriter(jsonFile, false));
            bw.write(mJson);
            bw.newLine();
            bw.flush();
            System.out.println("file writing ended");

        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("exception io :"+ioe.toString());
        } finally {
            if (bw != null) try {
                bw.close();
            } catch (IOException ioe2) {
                System.out.println("exception append: "+ioe2.toString());
            }
        }
    }


    public boolean checkFileExistance(){
        File file = new File(jsonFile);
        boolean exists = file.exists();
        if (file.exists() && file.isFile())
        {
            //
        }
        return exists;
    }

    public String quoteHandler(String st){
        String returnString= "";
        try{
            for(int i=0;i<st.length();i++){
                if(st.charAt(i)=='"'){
                    returnString += "\\";
                    returnString += "\"";
                }else{
                    returnString += st.charAt(i);
                }
            }
            return returnString;
        }catch (Exception e){
            System.out.println("error processing double quote: "+e.toString());
            return returnString;
        }
    }
}
