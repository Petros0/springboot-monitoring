package dev.petros0.monitoring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.repositories.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class MonitoringApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitoringApplication.class, args);
    }
}


@RestController
@Slf4j
@RequiredArgsConstructor
class DefaultController {

    @Autowired
    private ElasticLogRepo r;

    @Autowired
    private ElasticLogRepo2 r2;


    @GetMapping("/sysout")
    public Iterable<Log> sysout() {
        log.info("Hello World!!!");
        Iterable<Log> all = r.findAll();
        System.out.println(all);
        return all;
    }

    @GetMapping("/sysout2")
    public Log2 sysout2() {
        return r2.save(new Log2(null, "INFO", "Test"));
    }
}

@Document(indexName = "logs-*")
@Data
@NoArgsConstructor
@AllArgsConstructor
class Log {
    @Id
    @Field("_id")
    private String id;

    @Field(type = FieldType.Text)
    private String level;

    @Field(type = FieldType.Text)
    private String message;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "logs-wut")
class Log2 {
    @Id
    @Field("_id")
    private String id;

    @Field(type = FieldType.Text)
    private String level;

    @Field(type = FieldType.Text)
    private String message;
}

interface ElasticLogRepo
        extends ElasticsearchRepository<Log, String> {
    List<Log> findAllByLevelEquals(String level);
}

interface ElasticLogRepo2
        extends ElasticsearchRepository<Log2, String> {
    List<Log> findAllByLevelEquals(String level);
}