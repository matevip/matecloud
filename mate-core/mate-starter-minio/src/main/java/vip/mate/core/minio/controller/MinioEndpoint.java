package vip.mate.core.minio.controller;

import io.minio.ObjectStat;
import io.minio.messages.Bucket;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vip.mate.core.minio.core.MinioTemplate;
import vip.mate.core.minio.vo.MinioItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Minio服务端点
 * @author pangu
 */
@RestController
@AllArgsConstructor
@RequestMapping("${minio.endpoint.name:/minio}")
@ConditionalOnProperty(name = "minio.endpoint.enable", havingValue = "true")
public class MinioEndpoint {

    private final MinioTemplate template;

    /**
     * Bucket Endpoints
     */
    @SneakyThrows
    @PostMapping("/bucket/{bucketName}")
    public Bucket createBucker(@PathVariable String bucketName) {

        template.createBucket(bucketName);
        return template.getBucket(bucketName).get();

    }

    @SneakyThrows
    @GetMapping("/bucket")
    public List<Bucket> getBuckets() {
        return template.getAllBuckets();
    }

    @SneakyThrows
    @GetMapping("/bucket/{bucketName}")
    public Bucket getBucket(@PathVariable String bucketName) {
        return template.getBucket(bucketName).orElseThrow(() -> new IllegalArgumentException("Bucket Name not found!"));
    }

    @SneakyThrows
    @DeleteMapping("/bucket/{bucketName}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteBucket(@PathVariable String bucketName) {
        template.removeBucket(bucketName);
    }

    /**
     * Object Endpoints
     */
    @SneakyThrows
    @PostMapping("/object/{bucketName}")
    public ObjectStat createObject(@RequestBody MultipartFile object, @PathVariable String bucketName) {
        String name = object.getOriginalFilename();
        template.putObject(bucketName, name, object.getInputStream(), object.getSize(), object.getContentType());
        return template.getObjectInfo(bucketName, name);

    }

    @SneakyThrows
    @PostMapping("/object/{bucketName}/{objectName}")
    public ObjectStat createObject(@RequestBody MultipartFile object, @PathVariable String bucketName, @PathVariable String objectName) {
        template.putObject(bucketName, objectName, object.getInputStream(), object.getSize(), object.getContentType());
        return template.getObjectInfo(bucketName, objectName);

    }

    @SneakyThrows
    @GetMapping("/object/{bucketName}/{objectName}")
    public List<MinioItem> filterObject(@PathVariable String bucketName, @PathVariable String objectName) {

        return template.getAllObjectsByPrefix(bucketName, objectName, true);

    }

    @SneakyThrows
    @GetMapping("/object/{bucketName}/{objectName}/{expires}")
    public Map<String, Object> getObject(@PathVariable String bucketName, @PathVariable String objectName, @PathVariable Integer expires) {
        Map<String, Object> responseBody = new HashMap<>(8);
        // Put Object info
        responseBody.put("bucket", bucketName);
        responseBody.put("object", objectName);
        responseBody.put("url", template.getObjectURL(bucketName, objectName, expires));
        responseBody.put("expires", expires);
        return responseBody;
    }

    @SneakyThrows
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/object/{bucketName}/{objectName}/")
    public void deleteObject(@PathVariable String bucketName, @PathVariable String objectName) {

        template.removeObject(bucketName, objectName);
    }
}
