package com.leolmcoding;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class DocumentManagementSystem {
  private final Map<String, Importer> extensionToImporter = new HashMap<>();

  private final List<Document> documents = new ArrayList<>();
  public DocumentManagementSystem(){
      extensionToImporter.put("letter", new LetterImporter());
      extensionToImporter.put("report", new ReportImporter());
      extensionToImporter.put("jpg", new ImageImporter());
  }

  public void importFile(final String path) throws IOException {
      final File file = new File(path);
      if(!file.exists()){
          throw new FileNotFoundException(path);
      }
      final int separatorIndex = path.lastIndexOf(".");
      if(separatorIndex == -1 || separatorIndex == path.length()){
              throw new UnknownFileTypeException("No extension found for file : '"+path+"'");
      }
      final String extension = path.substring(separatorIndex+1);
      final Importer importer = extensionToImporter.get(extension);
      if (Objects.isNull(importer)) {
          throw new UnknowFileTypeException("For file type:"+path);
      }
      final Document document = importer.importFile(file);
      documents.add(document);
  }
}
