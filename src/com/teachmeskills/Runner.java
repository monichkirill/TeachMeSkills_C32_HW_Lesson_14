package com.teachmeskills;

import com.teachmeskills.exception.WrongDocException;
import com.teachmeskills.service.CheckDoc;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws WrongDocException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the path to the input file: ");
        String path = scanner.nextLine();

        CheckDoc.validateDocuments(path);
    }
}
