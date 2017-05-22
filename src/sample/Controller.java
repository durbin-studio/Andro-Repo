package sample;

import com.google.common.io.CharSource;
import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;
import com.jfoenix.controls.JFXTextArea;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Controller implements Serializable {

    List<RepoModel> models =  new ArrayList<>();
    JsonParser parser = new JsonParser();

    @FXML
    private Button btn_search;

    @FXML
    private Button btn_save;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_new;

    @FXML
    private TextField tv_search_field;

    @FXML
    private TextField tv_name;

    @FXML
    private ListView<String> list_search_result;

    @FXML
    private TextField tv_dependency;

    @FXML
    private TextField tv_permission;

    @FXML
    private JFXTextArea tv_code_snipet_area;

    @FXML
    private TextField tv_search_key;

    @FXML
    private CheckBox cb_isDeprecated;

    @FXML
    private CheckBox cb_isLayout;

    @FXML
    public void initialize() {
        setButtonListener();
        setCheckBoxListener();
        setListViewListener();

        if(App.getmInstance().getFileUtils().readFile() != null)models.addAll(parser.parseJson(App.getmInstance().getFileUtils().readFile()));

        for(int i=0;i<models.size();i++){
            addToView(models.get(i).getName());
        }

        btn_delete.setDisable(true);
        btn_save.setDisable(false);
        btn_new.setDisable(false);

        tv_search_field.setText("Temporarily Disabled");
        tv_search_field.setDisable(true);
        btn_search.setDisable(true);

//        tv_search_key.setDisable(true);
//        tv_permission.setDisable(true);
//        tv_dependency.setDisable(true);
//        tv_name.setDisable(true);
//        tv_code_snipet_area.setDisable(true);
    }

    private void setListViewListener(){
        list_search_result.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                viewClicked(list_search_result.getSelectionModel().getSelectedIndex());
            }
        });
    }

    private void setCheckBoxListener() {

    }

    private void setButtonListener() {
        btn_search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String skey = (tv_search_field.getText().toString());
                for(int i=0;i<models.size();i++){

                }
            }
        });

        btn_save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                RepoModel repoModel = new RepoModel();
                String _name=quoteHandler(quoteHandler(tv_name.getText().toString()));
                String _codeSnippet = quoteHandler(tv_code_snipet_area.getText().toString());
                String _dependency = quoteHandler(tv_dependency.getText().toString());
                String _permission = quoteHandler(tv_permission.getText().toString());
                String _searchKey = (tv_search_key.getText().toString());

                if(_name.isEmpty() || _name == null){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    alert.setContentText("You need to atleast add a name for the repo!");

                    alert.showAndWait();
                    return;
                }

                if(_codeSnippet.isEmpty() || _codeSnippet == null){
                    _codeSnippet = "No code to Show";
                }

                if(_dependency.isEmpty() || _dependency == null){
                    _dependency = "No dependency needed";
                }

                if(_permission.isEmpty() || _permission == null){
                    _permission = "No permission needed";
                }

                if(_searchKey.isEmpty() || _searchKey == null){
                    _searchKey = _name;
                }

                repoModel.setName(_name);
                repoModel.setCodeSnipet(_codeSnippet);
                repoModel.setDependency(_dependency);
                repoModel.setPermission(_permission);
                repoModel.setSearchKey(_searchKey);

                boolean _isXml = cb_isLayout.isSelected();
                boolean _isDeprecated = cb_isDeprecated.isSelected();
                repoModel.setXML(_isXml);
                repoModel.setDeprecated(_isDeprecated);

                models.add(repoModel);
                App.getmInstance().getFileUtils().rewriteJson(App.getmInstance().getFileUtils().createJosn(models));
                addToView(_name);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Succesfully added "+ _name.toUpperCase()+" to the library");

                alert.showAndWait();
            }
        });

        btn_new.setDisable(false);
        btn_delete.setDisable(false);

        btn_new.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearFields();
                tv_name.requestFocus();
                btn_new.setDisable(false);
                btn_delete.setDisable(true);
                btn_save.setDisable(false);
            }
        });

        btn_delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearFields();
                int index = list_search_result.getSelectionModel().getSelectedIndex();
                list_search_result.getItems().remove(index);
                models.remove(index);
                App.getmInstance().getFileUtils().rewriteJson(App.getmInstance().getFileUtils().createJosn(models));
                btn_new.setDisable(false);
                btn_delete.setDisable(true);
                btn_save.setDisable(false);
            }
        });
    }

    public void clearFields(){
        tv_name.setText("");
        tv_dependency.setText("");
        tv_permission.setText("");
        tv_search_key.setText("");
        tv_code_snipet_area.setText("");
        btn_delete.setDisable(true);
        btn_new.setDisable(false);
        btn_delete.setDisable(true);
        cb_isDeprecated.setSelected(false);
        cb_isLayout.setSelected(false);
    }

    public Controller(){
        App.getmInstance().setController(this);
    }

    public void addToView(String title){
        list_search_result.getItems().add(title);
        System.out.println("t: "+title);
    }

    public void setModels(List<RepoModel> _model){
        models.addAll(_model);
    }

    public String quoteHandler(String st){
        return st;
//        String returnString= "";
//        try{
//            for(int i=0;i<st.length();i++){
//                if(st.charAt(i)=='"'){
//                    returnString += "\\";
//                    returnString += "\"";
//                }else{
//                    returnString += st.charAt(i);
//                }
//            }
//            return returnString;
//        }catch (Exception e){
//            System.out.println("error processing double quote: "+e.toString());
//            return returnString;
//        }
    }

    public void viewClicked(int position){
        System.out.println("clicked on  position: "+position);
        btn_new.setDisable(false);
        btn_delete.setDisable(false);
        btn_save.setDisable(false);
        tv_name.setText(models.get(position).getName());
        tv_dependency.setText(models.get(position).getDependency());
        tv_permission.setText(models.get(position).getPermission());
        tv_search_key.setText(models.get(position).getSearchKey());
        try {
//            CharSource source = models.get(position).getCodeSnipet().toCharArray();

//            String sourceString = "public class ABC{"+models.get(position).getCodeSnipet()+"}";
//            String formattedCode = App.getmInstance().getFormatter().formatSource(sourceString);
            tv_code_snipet_area.setText(models.get(position).getCodeSnipet());
//            tv_code_snipet_area.setText(formattedCode);
//            System.out.println(formattedCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

        cb_isLayout.setSelected(models.get(position).isXML());
        cb_isDeprecated.setSelected(models.get(position).isDeprecated());
    }

}
