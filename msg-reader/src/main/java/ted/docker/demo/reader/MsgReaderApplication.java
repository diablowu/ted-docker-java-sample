package ted.docker.demo.reader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication

public class MsgReaderApplication {


	public static void main(String[] args) {
		SpringApplication.run(MsgReaderApplication.class, args);
	}
}


@RestController
class ReaderControll {

	@Autowired
	private StringRedisTemplate redis;

	@GetMapping("/{ch}/msg")
	public ResponseEntity<String> get(@PathVariable(name="ch") String ch){
		String r = redis.opsForList().leftPop(ch);
		if(StringUtils.hasText(r)){
			return ResponseEntity.ok("msg:{"+r +"},reader:"+System.getenv("HOSTNAME"));
		}else{
			return ResponseEntity.notFound().build();
		}
	}

}
