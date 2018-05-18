package ted.docker.demo.writer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class MsgWriterApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsgWriterApplication.class, args);
	}
}

@RestController
class WriterControll {

	@Autowired
	private StringRedisTemplate redis;

	@RequestMapping("/{ch}/msg")
	public ResponseEntity<String> get(@PathVariable(name = "ch") String ch,String msg) {
		long len = redis.opsForList().rightPush(ch,
		"msg:" + msg + ",ts:" + System.currentTimeMillis()+",writer:"+System.getenv("HOSTNAME"));
		return ResponseEntity.ok(String.valueOf(len));

	}

}