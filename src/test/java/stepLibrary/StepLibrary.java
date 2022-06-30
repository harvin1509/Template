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
    public void userLoadsXMLFile(String arg1, String arg2, String dataTableValues) {
        System.out.println("Hello");
    }
}
