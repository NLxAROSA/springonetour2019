package io.pivotal.workshop.workshopgreetingui;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class GreetingController {

  Logger logger = LoggerFactory
      .getLogger(GreetingController.class);

  private final FortuneService fortuneService;

  public GreetingController(FortuneService fortuneService) {
    this.fortuneService = fortuneService;
  }

  @RequestMapping("/")
  String getGreeting(Model model){

    logger.debug("Adding greeting");
    model.addAttribute("msg", "Greetings!!!");

    String fortune = fortuneService.getFortune();

    logger.debug("Adding fortune");
    model.addAttribute("fortune", fortune);

    // Adding env info
    try {
      Map<String, Object> modelMap = null;
      modelMap = this.addAppEnv();
      model.addAllAttributes(modelMap);
    } catch (Exception e) {
      logger.error(e.getMessage());
      e.printStackTrace();
    }
    
    //resolves to the greeting.html thymeleaf template
    return "greeting";
  }

  @RequestMapping("/kill")
  String killInstance(Model model){

    logger.debug("Adding greeting");
    model.addAttribute("msg", "Greetings!!!");

    String fortune = fortuneService.getFortune();

    logger.debug("Adding fortune");
    model.addAttribute("fortune", fortune);

    // Adding env info
    try {
      Map<String, Object> modelMap = null;
      modelMap = this.addAppEnv();
      model.addAllAttributes(modelMap);
    } catch (Exception e) {
      logger.error(e.getMessage());
      e.printStackTrace();
    }

    Thread killThread = new Thread(new Runnable() {
    public void run()
    {
	 try {	
	 	Thread.sleep(500);
         } catch (Exception e) {
	 	logger.warn("Attempting to kill instance: Thread could not sleep");
	 }
	 logger.info("Killing app instance");
	 System.exit(-1);
    }});  
    killThread.start();

    //resolves to the greeting.html thymeleaf template
    return "greeting";
  }



  /**
   * addAppEnv - Retrieve information about the application
   *
   * @return
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  Map<String, Object> addAppEnv() throws Exception {

    Map<String, Object> modelMap = new HashMap<String, Object>();

    String instanceIndex = getVcapApplicationMap().getOrDefault("instance_index", "no index environment variable")
            .toString();
    modelMap.put("instanceIndex", instanceIndex);

    String instanceAddr = System.getenv("CF_INSTANCE_ADDR");
    if (instanceAddr == null) {
      instanceAddr = "running locally";
    }
    modelMap.put("instanceAddr", instanceAddr);

    String applicationName = (String) getVcapApplicationMap().getOrDefault("application_name",
            "no name environment variable");
    modelMap.put("applicationName", applicationName);

    @SuppressWarnings("rawtypes")
    Map services = getVcapServicesMap();
    modelMap.put("applicationServices", services);

    // getting host local address
    try {
      InetAddress ipAddr = InetAddress.getLocalHost();
      modelMap.put("inetaddressLocalhost", ipAddr);
    } catch (UnknownHostException ex) {
      ex.printStackTrace();
    }

    return modelMap;
  }

  ///////////////////////////////////////
  // Helper Methods
  ///////////////////////////////////////

  @SuppressWarnings("rawtypes")
  private Map getVcapApplicationMap() throws Exception {
    return getEnvMap("VCAP_APPLICATION");
  }

  @SuppressWarnings("rawtypes")
  private Map getVcapServicesMap() throws Exception {
    return getEnvMap("VCAP_SERVICES");
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  private Map getEnvMap(String vcap) throws Exception {
    String vcapEnv = System.getenv(vcap);
    ObjectMapper mapper = new ObjectMapper();

    if (vcapEnv != null) {
      Map<String, ?> vcapMap = mapper.readValue(vcapEnv, Map.class);
      return vcapMap;
    }

    logger.warn(vcap + " not defined, returning empty Map");
    return new HashMap<String, String>();
  }


}
