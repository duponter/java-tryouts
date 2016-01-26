package edu.tryouts.easybatch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.easybatch.core.job.JobBuilder;
import org.easybatch.core.job.JobReport;
import org.easybatch.core.mapper.RecordMapper;
import org.easybatch.core.processor.ComputationalRecordProcessor;
import org.easybatch.core.reader.StringRecordReader;
import org.easybatch.core.record.GenericRecord;
import org.easybatch.core.record.StringRecord;

/**
 * http://www.easybatch.org/index.html
 */
public class EasyBatch {
	public static void main(String[] args) {

		String dataSource =
				"big data is often\n" +
						"not so big";

		JobReport report = JobBuilder.aNewJob()
				.reader(new StringRecordReader(dataSource))
				.mapper(new LineTokenizer())
				.processor(new WordCounter())
				.build().call();

		Map<String, Integer> words = (Map<String, Integer>) report.getResult();

		System.out.println(words);
	}

	static class LineTokenizer implements RecordMapper<StringRecord, GenericRecord> {

		public GenericRecord processRecord(StringRecord record) {
			String payload = record.getPayload();
			return new GenericRecord<>(record.getHeader(), Arrays.asList(payload.split(" ")));
		}
	}

	static class WordCounter implements
			ComputationalRecordProcessor<GenericRecord, GenericRecord, Map<String, Integer>> {

		private Map<String, Integer> words = new HashMap<>();

		public Map<String, Integer> getComputationResult() {
			return words;
		}

		public GenericRecord processRecord(GenericRecord record) {
			List<String> tokens = (List<String>) record.getPayload();
			for (String token : tokens) {
				Integer count = words.get(token);
				count = (count == null) ? 1 : count + 1;
				words.put(token, count);
			}
			return record;
		}
	}
}
