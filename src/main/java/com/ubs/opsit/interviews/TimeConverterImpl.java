package com.ubs.opsit.interviews;

import java.util.StringJoiner;
import java.util.stream.Stream;

public class TimeConverterImpl implements TimeConverter{

	@Override
	public String convertTime(String aTime) {
		if(null == aTime) {
			throw new IllegalArgumentException(String.format("Provided time is invalid",aTime));
		}
		
		try {
			
			int[] splittedTime = Stream.of(aTime.split(":", 3)).mapToInt(Integer::parseInt).toArray();
			if(splittedTime.length < 0) {
				throw new IllegalArgumentException(String.format("Provided time is invalid",aTime));
			}
			checkIfProvidedTimeValid(splittedTime);
			
			StringJoiner berlinTimeInString = new StringJoiner(":");
			return berlinTimeInString	.add(getSecondsFromBerlinClock(splittedTime[2]))
							  			.add(getTopRowHours(splittedTime[0]))
							  			.add(getBottomRowHours(splittedTime[0]))
							  			.add(getTopRowMins(splittedTime[1]))
							  			.add(getBottomRowMins(splittedTime[1])).toString();
		}catch(NumberFormatException e) {
			throw new IllegalArgumentException(String.format("Error while coneverting time %s into numeric value",aTime));
		}
		
	}
		public void checkIfProvidedTimeValid(final int[] splittedTime) {
			if(splittedTime[0] < 0 || splittedTime[0] > 23) {
				throw new IllegalArgumentException(String.format("Not valid hour %d",splittedTime[0]));
			}
			
			if(splittedTime[1] < 0 || splittedTime[1] > 59) {
				throw new IllegalArgumentException(String.format("Not valid minutes %d",splittedTime[1]));
			}
			
			if(splittedTime[2] < 0 || splittedTime[2] > 59) {
				throw new IllegalArgumentException(String.format("Not valid seconds %d",splittedTime[2]));
			}
			
		}

		public String getBottomRowMins(final int minutes) {
			return getOnOffLampsInRow(minutes % 5, 4, LightOnOffEnum.Yellow.getValue());
		
		}

		public String getTopRowMins(final int minutes) {
			
			return getOnOffLampsInRow(minutes / 5, 11, LightOnOffEnum.Yellow.getValue()).replaceAll("YYY", "YYR");
		}

		public String getBottomRowHours(final int hours) {
			return getOnOffLampsInRow(hours % 5, 4, LightOnOffEnum.Red.getValue());
		}

		public String getTopRowHours(final int hours) {
			return getOnOffLampsInRow(hours/5, 4, LightOnOffEnum.Red.getValue());
		}


		public String getSecondsFromBerlinClock(final int seconds) {
			return (seconds % 2 == 0) ? LightOnOffEnum.Yellow.getValue() : LightOnOffEnum.Off.getValue();
		}

		public String getOnOffLampsInRow(final int onLamps,final int lampsInRow, final String lampColor) {
			
			StringJoiner onOffLampInString = new StringJoiner("");
			for (int i = 0; i < onLamps; i++) {
				onOffLampInString.add(lampColor);
			}
			for (int i = 0; i < (lampsInRow - onLamps); i++) {
				onOffLampInString.add( LightOnOffEnum.Off.getValue());
			}
			return onOffLampInString.toString();
	        
		}
		
		
	}

