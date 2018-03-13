package org.focusrobotique.tools.pid.tools;

import java.io.IOException;
import java.time.LocalDateTime;

import org.focusrobotique.tools.pid.utils.DateUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link DateUtils}
 */
public class DateUtilsTest {
	
	@Test
	public void dateToString1() throws IOException {
		LocalDateTime startOfGoodWorldDateTime = LocalDateTime.of(1980,  11,  20,  10, 57, 00);
		String actual = DateUtils.dateToString(startOfGoodWorldDateTime);
		
		Assert.assertEquals("1980-11-20__10-57-00", actual);
	}
}
