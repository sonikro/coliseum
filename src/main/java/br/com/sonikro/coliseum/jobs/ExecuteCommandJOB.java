package br.com.sonikro.coliseum.jobs;

import org.jboss.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.sonikro.command.BaseCommand;

public class ExecuteCommandJOB implements Job{
	public static String COMMAND_KEY = "COMMAND";
	private BaseCommand mCommand;
	private static Logger logger = Logger.getLogger(ExecuteCommandJOB.class);
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("Running Command JOB "+context.getFireInstanceId());
		//mCommand = (BaseCommand) context.get(COMMAND_KEY);
		
		try {
			mCommand = (BaseCommand) context.getMergedJobDataMap().get(COMMAND_KEY);

			mCommand.dispatch();
			
		} catch (Exception e) {
			logger.info(e);
		}
		
	}

}