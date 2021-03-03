package com.geektrust.family.processor;

import com.geektrust.family.service.FamilyService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.geektrust.family.constants.StringConstants.*;

/**
 * To process inout file contents and process the value
 */
public class InputFileProcessor {

    public static final Integer RELATIONSHIP_PARAM_LENGTH = 4;
    public static final Integer ADD_CHILD_PARAM_LENGTH = 3;

    /**
     * scan the file and reads each line
     *
     * @param filePath      - filepath to read file from
     * @param familyService - service name
     */
    public void processInput(final String filePath, final FamilyService familyService, String separator) {
        File file = new File(filePath);
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String command = sc.nextLine();
                readInput(command, familyService, separator);
            }
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        }
    }

    /**
     * will read each input line  and add to the family hierarchy
     * empty space(;) will used as delimiter for splitting values
     *
     * @param inputLine     - processes each line of input file
     * @param familyService - service name
     */
    public void readInput(final String inputLine, final FamilyService familyService, String separator) {
        String[] inputParams = inputLine.split(separator);
        switch (inputParams[0]) {
            case ADD_CHILD:
                if (inputParams.length < RELATIONSHIP_PARAM_LENGTH) {
                    System.out.println(INVALID_COMMAND);
                } else {
                    familyService.addChild(inputParams[1], inputParams[2], inputParams[3]);
                }
                break;
            case ADD_SPOUSE:
                if (inputParams.length < RELATIONSHIP_PARAM_LENGTH) {
                    System.out.println(INVALID_COMMAND);
                } else {
                    familyService.addSpouse(inputParams[1], inputParams[2], inputParams[3]);
                }
                break;
            case GET_RELATIONSHIP:
                if (inputParams.length < ADD_CHILD_PARAM_LENGTH) {
                    System.out.println(INVALID_COMMAND);
                } else {
                    familyService.getRelationShip(inputParams[1], inputParams[2]);
                }
                break;
            default:
                System.out.println(INVALID_COMMAND);
        }
    }


}
