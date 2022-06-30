package stepLibrary;


import cucumber.api.DataTable;
import org.testng.Assert;
import org.testng.Reporter;
import org.w3c.dom.Document;
import support.Constants;
import utilities.CommonUtilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StepLibrary {

    static String filePath="";
    static Map<String, String> tableData;
    Map<Integer,String> writeTxt=new HashMap<Integer,String>();
    final static Charset ENCODING= StandardCharsets.UTF_8;
    protected static Document doc;
    public void blockCreationForFile(String scenario, String fileType, DataTable blockData) throws IOException {
        generateFlatFile(fileType, blockData, scenario, filePath);
        filePath=createFlatFileonLocal(fileType, blockData ,Constants.BASE_OUTPUT_Flat_FILE_PATH , scenario);

        if(filePath.equalsIgnoreCase("Error Flat File not created")){
            Reporter.log("Error while creating Flat File" + fileType);
            Assert.fail("Flat file not created due to exception");
        }
        writeLargerTextFileAlternative(filePath,writeTxt);
    }

    private void writeLargerTextFileAlternative(String filePath, Map<Integer, String> writeTxt) throws IOException {
        Path path= Paths.get(filePath);
        try(BufferedWriter writer= Files.newBufferedWriter(path,ENCODING)){
            for(Integer key : writeTxt.keySet()){
                writer.write(writeTxt.get(key));
                writer.newLine();
            }
        }
    }

    private String createFlatFileonLocal(String fileType, DataTable blockData, String base_output_flat_file_path, String scenario) {
        try{
            String dateString= CommonUtilities.getTimeStampString("yyMMdd");
            String fileFolder="";
            String outputFilePath="";
            String FlatFilePath="";
            String fileExtension="";
            int fileCounter=0;
            switch(fileType) {
                case "txt":
                    fileFolder = base_output_flat_file_path + "/" + fileType + CommonUtilities.getTimeStampString("dd-MM-yy");
                    outputFilePath = fileFolder + "/" +fileType + dateString;
                    break;
                default:
                    break;
            }
            if(!new File(fileFolder).exists()){
                new File(fileFolder).mkdirs();
            }
            fileCounter=CommonUtilities.countNumberOfFilesinFolder(new File(fileFolder));
            FlatFilePath=outputFilePath+fileCounter+fileExtension;
            System.out.println(FlatFilePath);
            return FlatFilePath;

        } catch (Exception e) {
            e.printStackTrace();
            return "Error Flat File not created";
        }
}
    public void generateFlatFile(String fileType, DataTable blockData, String block, String filePath) {
        String block1Param=null, block2Param=null, block3Param=null, finalString=null;
        Map<String , String > myList= new HashMap<String , String>();
        tableData=new HashMap<String, String>();
        myList=getValueFromDataTable(blockData, 0);

        Iterator iterator=myList.keySet().iterator();
        while(iterator.hasNext()){
            String key= iterator.next().toString();
            String value=myList.get(key).toString();

            if(key.equalsIgnoreCase("Receiver")){
                block1Param=value;
                tableData.put("Receiver", block1Param);
            }
            if(key.equalsIgnoreCase("Sender")){
                block2Param=value;
                tableData.put("Sender", block2Param);
            }
            if(key.equalsIgnoreCase("BIC")){
                block3Param=value;
                tableData.put("block3Param", block3Param);
            }
        }
        block1Param=getBlock(block1Param);
        block2Param=getBlock(block2Param);

        System.out.println(block1Param);
        finalString=block1Param+block2Param+"{3:"+block3Param +"}";
        writeTxt.put(1,finalString);
    }

    public String getBlock(String blockParam){
        String blockA="{";
        String blockB=blockParam;
        String blockC=CommonUtilities.getTimeStampString("ddMMyyhhss") + "}";
        String strFinal= blockA+blockB+blockC;
        return strFinal;
    }

    private Map<String, String> getValueFromDataTable(DataTable blockData, int rownumber) {
        List<Map<String,String>> list=blockData.asMaps(String.class, String.class);
        return list.get(rownumber);
    }

    public void userLoadsTheTxtFileScenarioNameForFileTypeForDatePublishedOnDayD(String folder, String fileType, String dataPublishedday) {
        int fileCounter=0;
        String fileFolder="";
        String outputFilePath="";
        String dateString= CommonUtilities.getDateBasedOnCharacterBeforeWorkingDays(dataPublishedday, "yyMMdd");

        switch(fileType) {
            case "file-type":
                fileFolder = Constants.BASE_OUTPUT_Flat_FILE_PATH + "/" + fileType + CommonUtilities.getTimeStampString("dd-MM-yy");
                fileCounter=CommonUtilities.countNumberOfFilesinFolder(new File((fileFolder)));
                outputFilePath = fileFolder + "/" + fileType + dateString+fileCounter+".txt";
                break;
            default:
                break;
        }
        //loadDataForTXTFileType(fileType);
       // doc= CommonUtilities.initializeXMLDocument(new File(filePath));
        System.out.println("outputFilePath"+ filePath);
        if(doc!=null) {
            Reporter.log("Input XML FIle successfully loaded into memory and FilePath=" + filePath);
            System.out.println("Input XML FIle successfully loaded into memory and FilePath=" + filePath);
        } else if (new File(outputFilePath).exists()) {
            Reporter.log("reading Input XML FIle");
            System.out.println(".txt file present in " + filePath);
        } else {
            if(!new File(filePath).exists()){
                Reporter.log("Error while reading Input XML FIle");
                Assert.fail("Error while reading file");
            }
        }
    }

    private void loadDataForTXTFileType(String fileType) {
    }

    public void userLoadsXMLFile(String folder, String fileType, String datePublishedday) {
    }
}
