package br.com.sonikro.coliseum.jobs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import br.com.sonikro.coliseum.producers.EntityManagerProducer;
import br.com.sonikro.command.BaseCommand;

public class SimpleJobManager {
	
	public void run(JobDetail job) throws SchedulerException {
		SimpleScheduleBuilder schedule =  SimpleScheduleBuilder.simpleSchedule()
			  	.withRepeatCount(0);
		
		Trigger trigger = TriggerBuilder.newTrigger()
		.withIdentity(job.getKey().toString())
		.withSchedule(schedule)
		.build();

		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		if(scheduler.isInStandbyMode())
		{
			scheduler.start();
		}
		scheduler.scheduleJob(job, trigger);
	}
	
	public void run(BaseCommand command) throws SchedulerException
	{
		JobDataMap jobData = new JobDataMap();
		
		EntityManagerFactory emf = EntityManagerProducer.getManagerFactory();
		//Inject EntityManagerFactory to the Command, so it can deal with database operations
		command.setStartObjects(emf);
		
		jobData.put(ExecuteCommandJOB.COMMAND_KEY, command);
		
		JobDetail job = JobBuilder.newJob(ExecuteCommandJOB.class)
				.usingJobData(jobData)
				.build();

		this.run(job);
	}
	
}
