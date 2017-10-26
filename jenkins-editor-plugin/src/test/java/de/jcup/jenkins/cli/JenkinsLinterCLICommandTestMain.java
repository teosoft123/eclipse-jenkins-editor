package de.jcup.jenkins.cli;

import java.io.File;

import de.jcup.jenkins.cli.JenkinsCLIConfiguration;
import de.jcup.jenkins.cli.JenkinsLinterCLICommand;
import de.jcup.jenkins.cli.JenkinsLinterCLIResult;
import de.jcup.jenkins.cli.JenkinsCLIConfiguration.AuthMode;

public class JenkinsLinterCLICommandTestMain {

	/* just an api key for an temporary test jenkins*/
	
	public static void main(String[] args) throws Exception {
		if (args.length!=2){
			throw new IllegalArgumentException("Call with parameters:'userName apiKEY' !");
		}
		String user=args[0];
		String apiKey= args[1];
		JenkinsLinterCLICommandTestMain testMain =  new JenkinsLinterCLICommandTestMain();
		testMain.test(user, apiKey);
		
	}
	
	public void test(String user, String apiKey) throws Exception{
		// https://jenkins.io/doc/book/managing/cli/
		JenkinsCLIConfiguration config = new JenkinsCLIConfiguration();
		config.setJenkinsURL("http://localhost:8080");
		config.setTimeoutInSeconds(10);
		config.setPathToJenkinsCLIJar("./lib/jenkins-cli.jar");
		config.setAuthMode(AuthMode.APIKEY);
		config.setUser(user);
		config.setAPIToken(apiKey);
		
		JenkinsLinterCLICommand cmd = new JenkinsLinterCLICommand();
		JenkinsLinterCLIResult result = cmd.execute(config,new File("./src/test/resources/JenkinsFile1"));
	
		System.out.println("result:"+ result.toString());
	}
	
}
