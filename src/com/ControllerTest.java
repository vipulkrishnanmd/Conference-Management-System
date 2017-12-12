package com;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.junit.Test;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class ControllerTest extends Controller {
	
	/*
	 * Test searchConference function 
	 */
	@Test
	public void testSearchConference() {
		ArrayList<Conference> confList = searchConference();
		assertNotNull(confList);
	}
	
	/*
	 * To ensure function works is the parameters are all good.
	 */
	@Test
	public void testCreateNewPaper1() {
		InputStream is = new ByteArrayInputStream( "vipu".getBytes() );
		assertTrue(createNewPaper("test", "test", 0, 0, is));
	}
	
	/*
	 * To test a null inputStream as parameter
	 */
	@Test()
	public void testCreateNewPaper2() {
		assertFalse(createNewPaper("test", "test", 0, 0, null));
	}
	
	/*
	 * To ensure the system does not accept null instead of the strings
	 */
	@Test
	public void testCreateNewPaper3() {
		InputStream is = new ByteArrayInputStream( "vipu".getBytes() );
		assertFalse(createNewPaper(null, "test", 0, 0, is));
	}
	
	/*
	 * To ensure the system does not accept blank string as parameter
	 */
	@Test
	public void testCreateNewPaper4() {
		InputStream is = new ByteArrayInputStream( "vipu".getBytes() );
		assertFalse(createNewPaper("", "test", 0, 0, is));
	}
	
	/*
	 * To ensure that the system checks for invalid CID and UID
	 */
	@Test
	public void testCreateNewPaper5() {
		InputStream is = new ByteArrayInputStream( "vipu".getBytes() );
		assertFalse(createNewPaper("test", "test", 150, 150, is));
	}
	
	/*
	 * Trying to add a reviewer with valid cid, pid and uid
	 */
	@Test
	public void testAssignReviewer1() {
		assertTrue(assignReviewer(0,2,13));
	}
	
	/*
	 * Doing the same again
	 * This should show false since we are trying to add same person again
	 */
	@Test
	public void testAssignReviewer2() {
		assertFalse(assignReviewer(0,2,13));
	}
	
	/*
	 * Doing the same again after 4 attempts
	 * This should show false since the max number of reviewer is 4
	 */
	@Test
	public void testAssignReviewer3() {
		assertFalse(assignReviewer(0,2,13));
	}
	
	/*
	 * Invalid UID
	 */
	@Test
	public void testAssignReviewer4() {
		assertFalse(assignReviewer(150,2,13));
	}
	
	/*
	 * Invalid CID
	 */
	@Test
	public void testAssignReviewer5() {
		assertFalse(assignReviewer(0,150,13));
	}
	
	/*
	 * Invalid PID
	 */
	@Test
	public void testAssignReviewer6() {
		assertFalse(assignReviewer(0,2,150));
	}
	
	/*
	 * All parameters good
	 */
	@Test
	public void testGetSuitableRev1() {
		assertNotNull(getSuitableRev(0,0));
	}
	
	/*
	 * Invalid CID
	 * Should return null
	 */
	@Test
	public void testGetSuitableRev2() {
		assertNull(getSuitableRev(150,0));
	}
	
	/*
	 * Invalid PID
	 * SHould return null
	 */
	@Test
	public void testGetSuitableRev3() {
		assertNull(getSuitableRev(0,150));
	}

}
