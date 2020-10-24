package censusanalyser;

import com.bridgelabz.csvbuilder.CSVException;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class CensusAnalyserTest {
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String CENSUS_WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String CENSUS_WRONG_CSV_FILE_EXTENSION = "./src/test/resources/IndiaStateCensusData.bin";
    private static final String WRONG_CSV_FILE_DELIMITER = "./src/test/resources/WrongDelimiterData.csv";
    private static final String WRONG_CSV_FILE_HEADER = "./src/test/resources/WrongFileHeader.csv";
    private static final String INDIAN_STATE_CODE_CSV_FILE = "./src/test/resources/IndianStateCode.csv";
    private static final String CODE_WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCode.csv";
    private static final String CODE_WRONG_CSV_FILE_EXTENSION = "./src/test/resources/IndianStateCode.txt";

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CSVException.class);
            censusAnalyser.loadIndiaCensusData(CENSUS_WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFileExtension_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CSVException.class);
            censusAnalyser.loadIndiaCensusData(CENSUS_WRONG_CSV_FILE_EXTENSION);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFileType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CSVException.class);
            censusAnalyser.loadIndiaCensusData(INDIAN_STATE_CODE_CSV_FILE);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFileDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CSVException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_DELIMITER);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
            Assert.assertEquals(CensusAnalyserException.ExceptionType.HEADER_DELIMITER_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFileHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CSVException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_HEADER);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
            Assert.assertEquals(CensusAnalyserException.ExceptionType.HEADER_DELIMITER_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndianCodeData(INDIAN_STATE_CODE_CSV_FILE);
            Assert.assertEquals(37, numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndiaStateCodeData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CSVException.class);
            censusAnalyser.loadIndianCodeData(CODE_WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeData_WithWrongFileExtension_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CSVException.class);
            censusAnalyser.loadIndianCodeData(CODE_WRONG_CSV_FILE_EXTENSION);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateData_WithWrongFileType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CSVException.class);
            censusAnalyser.loadIndianCodeData(INDIA_CENSUS_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenStateCodeData_WithWrongFileDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CSVException.class);
            censusAnalyser.loadIndianCodeData(WRONG_CSV_FILE_DELIMITER);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
            Assert.assertEquals(CensusAnalyserException.ExceptionType.HEADER_DELIMITER_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeData_WithWrongFileHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CSVException.class);
            censusAnalyser.loadIndianCodeData(WRONG_CSV_FILE_HEADER);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
            Assert.assertEquals(CensusAnalyserException.ExceptionType.HEADER_DELIMITER_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianCensusCSVFile_whenCorrectAndUsingCommonsCSV_returnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusDataCommonsCSV(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void giveIndianStateData_WhenSortedOnStateNames_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
            Assert.assertEquals("West Bengal", censusCSV[censusCSV.length - 1].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_whenSortedOnStateCode_shouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndianCodeData(INDIAN_STATE_CODE_CSV_FILE);
            String sortedCensusData = censusAnalyser.getStateCodeWiseSortedCensusData();
            IndiaStateCodeCSV[] codeCSV = new Gson().fromJson(sortedCensusData, IndiaStateCodeCSV[].class);
            Assert.assertEquals("AD", codeCSV[0].stateCode);
            Assert.assertEquals("WB", codeCSV[codeCSV.length - 1].stateCode);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void giveIndianStateData_WhenSortOnPopulation_ShouldReturnSortedResult2() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData = censusAnalyser.getPopulationWiseSortedCensusData();
            IndiaCensusCSV[] indiaCensusCSV= new Gson().fromJson(sortCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(199812341, indiaCensusCSV[0].population);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void giveIndianStateData_WhenSortOnPopulation_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData = censusAnalyser.getPopulationWiseSortedCensusData();
            IndiaCensusCSV[] indiaCensusCSV= new Gson().fromJson(sortCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(199812341, indiaCensusCSV[0].population);
            Assert.assertEquals(607688, indiaCensusCSV[indiaCensusCSV.length-1].population);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void giveIndianStateData_WhenSortOnPopulationDensity_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData = censusAnalyser.getPopulationDensityWiseSortedCensusData();
            IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(sortCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(1102, indiaCensusCSV[0].densityPerSqKm);
            Assert.assertEquals(50, indiaCensusCSV[indiaCensusCSV.length-1].densityPerSqKm);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void giveIndianStateData_WhenSortOnArea_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData = censusAnalyser.getAreaWiseSortedCensusData();
            IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(sortCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Rajasthan", indiaCensusCSV[0].state);
            Assert.assertEquals("Goa", indiaCensusCSV[indiaCensusCSV.length-1].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();

        }
    }

}
