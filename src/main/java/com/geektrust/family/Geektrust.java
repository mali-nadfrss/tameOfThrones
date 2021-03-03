package com.geektrust.family;

import com.geektrust.family.model.Person;
import com.geektrust.family.processor.InputFileProcessor;
import com.geektrust.family.service.FamilyService;
import com.geektrust.family.serviceImpl.FamilyServiceImpl;

import static com.geektrust.family.constants.StringConstants.*;

/**
 * Geektrust will be the initial class for the project
 * with main method , run from here with desired input
 */
public final class Geektrust {

    public static final Person FAMILY_HEAD;

    /*
     * static initializer to initialize the family head
     */
    static {
        FAMILY_HEAD = new Person(QUEEN_ANGA, FEMALE);
    }

    /**
     * private constructor
     */
    private Geektrust() {

    }

    /**
     * main method to initialise the input file and process all files
     *
     * @param args - inout args
     */
    public static void main(final String[] args) {
        InputFileProcessor inputFileProcessor = new InputFileProcessor();
        FamilyService familyService = new FamilyServiceImpl();
        inputFileProcessor.processInput(RESOURCE_PATH + FILE_SEPARATOR + INIT_FILE_NAME, familyService, SEMI_COLON);
        if (args != null) {
            for (String arg : args) {
                inputFileProcessor.processInput(arg, familyService, EMPTY_SPACE);
            }
        }
    }
}
