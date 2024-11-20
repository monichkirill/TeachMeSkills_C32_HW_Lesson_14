package com.teachmeskills.service;

import com.teachmeskills.exception.WrongDocException;
import com.teachmeskills.util.Constants;

import java.io.*;

public class CheckDoc {
    public static void validateDocuments(String inputPathFile) throws WrongDocException {
        try(BufferedReader fileReader = new BufferedReader(new FileReader(inputPathFile));
            BufferedWriter docNumWriter = new BufferedWriter(new FileWriter(Constants.DOC_VALID));
            BufferedWriter contractWriter = new BufferedWriter(new FileWriter(Constants.CONTRACT_VALID));
            BufferedWriter invalidWriter = new BufferedWriter(new FileWriter(Constants.INVALID_DOC))
        ) {
            String line;

            while((line = fileReader.readLine()) != null) {
                line = line.trim();

                try{
                    validateDocument(line);

                    if(line.startsWith(Constants.DOCNUM_NAME)) {
                        docNumWriter.write(line + "\n");
                        System.out.println("Valid DOCNUM: " + line);
                    } else if (line.startsWith(Constants.CONTRACT_NAME)) {
                        contractWriter.write(line + "\n");
                        System.out.println("Valid CONTRACT: " + line);
                    }
                } catch (WrongDocException e) {
                    String invalid = line + " - " + e.getMessage();
                    invalidWriter.write(invalid + "\n");
                    System.out.println("Invalid Document: " + invalid);
                }
            }

        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }

    public static void validateDocument(String inputPathFile) throws WrongDocException {
        if (inputPathFile.length() != Constants.LENGTH)
            throw new WrongDocException("Invalid length");
        if (!(inputPathFile.startsWith(Constants.DOCNUM_NAME) || inputPathFile.startsWith(Constants.CONTRACT_NAME)))
            throw new WrongDocException("Invalid file");
        if (!inputPathFile.matches("^[a-zA-Z0-9]+$"))
            throw new WrongDocException("Invalid characters");
    }
}
