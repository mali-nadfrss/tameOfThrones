package com.geektrust.family.serviceImpl;

import com.geektrust.family.Geektrust;
import com.geektrust.family.enums.Gender;
import com.geektrust.family.enums.Relation;
import com.geektrust.family.utility.FamilyServiceUtility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class FamilyServiceImplTest {

    @InjectMocks
    FamilyServiceImpl familyService = new FamilyServiceImpl();

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        Geektrust.main(null);
    }

    @Test
    public void test_AddChild_FAILS_When_Person_Not_Found() {
        familyService.addChild("test_mother", "test_Child", "Male");
        Assert.assertNull(FamilyServiceUtility.findMember("test_Child", Geektrust.FAMILY_HEAD));
    }

    @Test
    public void test_AddChild_SUCCESS() {
        familyService.addChild("Lika", "Vika", "Male");
        Assert.assertNotNull(FamilyServiceUtility.findMember("Vika", Geektrust.FAMILY_HEAD));
        Assert.assertEquals(Gender.MALE, FamilyServiceUtility.findMember("Vika", Geektrust.FAMILY_HEAD).getGender());
    }

    @Test
    public void test_AddChild_FAILS_When_Spouse_Is_Null() {
        familyService.addChild("Lika", "Vika", Gender.MALE.getValue());
        familyService.addChild("Vika", "Noka", Gender.MALE.getValue());
        Assert.assertNull(FamilyServiceUtility.findMember("Noka", Geektrust.FAMILY_HEAD));
    }

    @Test
    public void test_findMember_SUCCESS_When_Person_Is_Found() {
        Assert.assertNotNull(familyService.findMember("Lika", Geektrust.FAMILY_HEAD));
    }

    @Test
    public void test_getRelationShip_Son_Success() {
        String result = familyService.getRelationShip("Dritha", Relation.SON.getValue());
        Assert.assertEquals("Yodhan", result);
    }

    @Test
    public void test_getRelationShip_Son_Returns_Null() {
        String result = familyService.getRelationShip("Lika", Relation.SON.getValue());
        Assert.assertEquals("", result);
    }

    @Test
    public void test_getRelationShip_DAUGHTER_Success() {
        String result = familyService.getRelationShip("Arit", Relation.DAUGHTER.getValue());
        Assert.assertEquals("Lavnya", result);
    }

    @Test
    public void test_getRelationShip_DAUGHTER_Returns_Null() {
        String result = familyService.getRelationShip("Vasa", Relation.DAUGHTER.getValue());
        Assert.assertEquals("", result);
    }

    @Test
    public void test_getRelationShip_SIBLINGS_Success() {
        String result = familyService.getRelationShip("Ish", Relation.SIBLINGS.getValue());
        Assert.assertEquals("Satya Aras Chit Vich", result);
    }

    @Test
    public void test_getRelationShip_SIBLINGS_Returns_Null() {
        String result = familyService.getRelationShip("Vasa", Relation.SIBLINGS.getValue());
        Assert.assertEquals("", result);
    }

    @Test
    public void test_getRelationShip_BROTHER_IN_LAW_Success() {
        String result = familyService.getRelationShip("Jaya", Relation.BROTHER_IN_LAW.getValue());
        Assert.assertEquals("Vritha", result);
    }

    @Test
    public void test_getRelationShip_BROTHER_IN_LAW_Returns_Null() {
        String result = familyService.getRelationShip("Yodhan", Relation.BROTHER_IN_LAW.getValue());
        Assert.assertEquals("", result);
    }

    @Test
    public void test_getRelationShip_SISTER_IN_LAW_Success() {
        String result = familyService.getRelationShip("Satvy", Relation.SISTER_IN_LAW.getValue());
        Assert.assertEquals("Atya Krpi", result);
    }

    @Test
    public void test_getRelationShip_SISTER_IN_LAW_Returns_Null() {
        String result = familyService.getRelationShip("Vasa", Relation.SISTER_IN_LAW.getValue());
        Assert.assertEquals("", result);
    }

    @Test
    public void test_getRelationShip_MATERNAL_AUNT_Success() {
        String result = familyService.getRelationShip("Yodhan", Relation.MATERNAL_AUNT.getValue());
        Assert.assertEquals("Tritha", result);
    }

    @Test
    public void test_getRelationShip_MATERNAL_AUNT_Returns_Null() {
        String result = familyService.getRelationShip("Tritha", Relation.MATERNAL_AUNT.getValue());
        Assert.assertEquals("", result);
    }

    @Test
    public void test_getRelationShip_PATERNAL_AUNT_Success() {
        String result = familyService.getRelationShip("Vasa", Relation.PATERNAL_AUNT.getValue());
        Assert.assertEquals("Atya", result);
    }

    @Test
    public void test_getRelationShip_PATERNAL_AUNT_Returns_Null() {
        String result = familyService.getRelationShip("Lavnya", Relation.PATERNAL_AUNT.getValue());
        Assert.assertEquals("", result);
    }

    @Test
    public void test_getRelationShip_MATERNAL_UNCLE_Success() {
        String result = familyService.getRelationShip("Laki", Relation.MATERNAL_UNCLE.getValue());
        Assert.assertEquals("Ahit", result);
    }

    @Test
    public void test_getRelationShip_MATERNAL_UNCLE_Returns_Null() {
        String result = familyService.getRelationShip("Satvy", Relation.MATERNAL_UNCLE.getValue());
        Assert.assertEquals("", result);
    }

    @Test
    public void test_getRelationShip_PATERNAL_UNCLE_Success() {
        String result = familyService.getRelationShip("Kriya", Relation.PATERNAL_UNCLE.getValue());
        Assert.assertEquals("Asva", result);
    }

    @Test
    public void test_getRelationShip_PATERNAL_UNCLE_Returns_Null() {
        String result = familyService.getRelationShip("Lavnya", Relation.PATERNAL_UNCLE.getValue());
        Assert.assertEquals("", result);
    }


}