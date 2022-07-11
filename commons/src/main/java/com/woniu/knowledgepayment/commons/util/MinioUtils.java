package com.woniu.knowledgepayment.commons.util;

import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.*;
// import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MinioUtils {

    private static final Logger log = LoggerFactory.getLogger(MinioUtils.class);

    private final String endpoint;
    private final String accessKey;
    private final String secretKey;

    private MinioClient minioClient;

    public MinioUtils(String endpoint, String accessKey, String secretKey) {
        this.endpoint = endpoint;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.minioClient = MinioClient.builder().endpoint(endpoint).credentials(accessKey, secretKey).build();
    }

/*
    @PostConstruct
    private MinioClient client() {
    }
*/

    public boolean doesBucketExists(String bucketName) {
        BucketExistsArgs args = BucketExistsArgs.builder()
                .bucket(bucketName)
                .build();
        try {
            return minioClient.bucketExists(args);
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 创建 bucket
     *
     * @param bucketName 桶名
     */
    public void createBucket(String bucketName) {
        BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder().bucket(bucketName).build();
        MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder().bucket(bucketName).build();

        try {
            if (minioClient.bucketExists(bucketExistsArgs))
                return;

            minioClient.makeBucket(makeBucketArgs);
        } catch (Exception e) {
            log.error("创建桶失败：{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }


    /**
     * 判断文件是否存在
     *
     * @param bucketName 存储桶
     * @param objectName 对象
     * @return true：存在
     */
    public boolean doesObjectExist(String bucketName, String objectName) {
        StatObjectArgs args = StatObjectArgs.builder().bucket(bucketName).object(objectName).build();
        try {
            minioClient.statObject(args);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    /**
     * 判断文件夹是否存在
     *
     * @param bucketName 存储桶
     * @param objectName 文件夹名称（去掉/）
     * @return true：存在
     */
    public boolean doesFolderExist(String bucketName, String objectName) {
        ListObjectsArgs args = ListObjectsArgs.builder()
                .bucket(bucketName)
                .prefix(objectName)
                .recursive(false)
                .build();
        boolean exist = false;
        try {
            Iterable<Result<Item>> results = minioClient.listObjects(args);
            for (Result<Item> result : results) {
                Item item = result.get();
                if (!item.isDir())
                    continue;

                if (objectName.equals(item.objectName())) {
                    exist = true;
                }
            }
        } catch (Exception e) {
            exist = false;
        }
        return exist;
    }


    /**
     * 通过 MultipartFile ，上传文件
     *
     * @param bucketName 存储桶
     * @param file       文件
     * @param objectName 对象名
     */
    public ObjectWriteResponse putObject(String bucketName, MultipartFile file, String objectName, String contentType) {
        try {
            InputStream inputStream = file.getInputStream();
            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .contentType(contentType)
                    .stream(inputStream, inputStream.available(), -1)
                    .build();
            return minioClient.putObject(args);
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 上传本地文件
     *
     * @param bucketName 存储桶
     * @param objectName 对象名称
     * @param fileName   本地文件路径
     */
    public ObjectWriteResponse putObject(String bucketName, String objectName, String fileName) {
        try {
            UploadObjectArgs args = UploadObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .filename(fileName)
                    .build();
            return minioClient.uploadObject(args);
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过流上传文件
     *
     * @param bucketName  存储桶
     * @param objectName  文件对象
     * @param inputStream 文件流
     */
    public ObjectWriteResponse putObjectByStream(String bucketName, String objectName, InputStream inputStream) {
        try {
            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(inputStream, inputStream.available(), -1)
                    .build();
            return minioClient.putObject(args);
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建文件夹或目录
     *
     * @param bucketName 存储桶
     * @param objectName 目录路径
     */
    public ObjectWriteResponse putDirObject(String bucketName, String objectName) {
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .stream(new ByteArrayInputStream(new byte[]{}), 0, -1)
                .build();
        try {
            return minioClient.putObject(args);
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 获取上传文件信息上传文件
     *
     * @param file       上传文件
     * @param bucketName 桶名

    public JSONObject uploadFile(MultipartFile file, String bucketName) throws Exception {
        JSONObject res = new JSONObject();
        res.put("code", 0);
        //判断文件是否为空
        if (null == file || 0 == file.getSize()) {
            res.put("msg", "上传文件不能为空");
            return res;
        }
        // 判断存储桶是否存在  不存在则创建
        createBucket(bucketName);
        // 文件名
        String originalFilename = file.getOriginalFilename();
        // 新的文件名 = 存储桶文件名_时间戳.后缀名
        String fileName = bucketName + "_" +
                System.currentTimeMillis() +
                StringUtils.getFilenameExtension(originalFilename);
//                originalFilename.substring(originalFilename.lastIndexOf("."));
        // 开始上传
        putObjectByStream(bucketName, fileName, file.getInputStream());
        res.put("code", 1);
        res.put("fileName", fileName);
        res.put("msg", String.format("%s/%s/%s", endpoint, bucketName, fileName));
//        res.put("msg", endpoint + "/" + bucketName + "/" + fileName);
        return res;
    }
*/

    /**
     * 获取文件路径上传文件
     *
     * @param filepath   文件路径，bucketName 桶名 Uuid UUID
     * @param uuid       文件UUID
     * @param bucketName com.alibaba.fastjson.JSONObject

    public JSONObject uploadFile(String filepath, String uuid, String bucketName) throws Exception {
//        MultipartFile file = null;
        File oldFile = new File(filepath);
        FileInputStream fileInputStream = new FileInputStream(oldFile);
//        file = new MockMultipartFile(oldFile.getName(), oldFile.getName(), fileInputStream);
        JSONObject res = new JSONObject();
        res.put("code", 0);
        // 判断文件是否为空
        if (StringUtils.isEmpty(oldFile)) {
            res.put("msg", "上传文件不能为空");
            return res;
        }
        // 判断存储桶是否存在  不存在则创建
        createBucket(bucketName);
        // 文件名
        String originalFilename = oldFile.getName();
        // 新的文件名 = 存储桶文件名_时间戳_UUID.后缀名
        String fileName = bucketName + "_" +
                System.currentTimeMillis() + "_" + uuid +
                originalFilename.substring(originalFilename.lastIndexOf("."));
        // 开始上传
        putObjectByStream(bucketName, fileName, fileInputStream);
        res.put("code", 1);
        res.put("fileName", fileName);
        res.put("msg", endpoint + "/" + bucketName + "/" + fileName);
        return res;
    }
     */

    /**
     * 获取文件路径上传文件
     *
     * @param bucketName com.alibaba.fastjson.JSONObject
     * @param filepath   文件路径，bucketName 桶名

    public JSONObject uploadFile(String filepath, String bucketName) throws Exception {
//        MultipartFile file = null;
        File oldFile = new File(filepath);
        FileInputStream fileInputStream = new FileInputStream(oldFile);
//        file = new MockMultipartFile(oldFile.getName(), oldFile.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
        JSONObject res = new JSONObject();
        res.put("code", 0);
        //判断文件是否为空
        if (StringUtils.isEmpty(oldFile)) {
            res.put("msg", "上传文件不能为空");
            return res;
        }
        //判断存储桶是否存在  不存在则创建
        createBucket(bucketName);
        //文件名
        String originalFilename = oldFile.getName();
        //新的文件名 = 存储桶文件名_时间戳_UUID.后缀名
        String fileName = bucketName + "_" +
                System.currentTimeMillis() +
                originalFilename.substring(originalFilename.lastIndexOf("."));
        //开始上传
        putObjectByStream(bucketName, fileName, fileInputStream);
        res.put("code", 1);
        res.put("fileName", fileName);
        res.put("msg", endpoint + "/" + bucketName + "/" + fileName);
        return res;
    }
     */

    /**
     * 获取全部 bucket
     */
    public List<Bucket> getAllBuckets() throws Exception {
        return minioClient.listBuckets();
    }

    /**
     * 根据 bucketName 删除信息
     *
     * @param bucketName 桶名
     */
    public void removeBucket(String bucketName) {
        try {
            minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取⽂件外链
     *
     * @param bucketName bucket名称
     * @param objectName ⽂件名称
     * @param expires    过期时间 <=7
     */
    public String getObjectUrl(String bucketName, String objectName, Integer expires) {
        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(bucketName)
                .object(objectName)
                .expiry(expires)    // 单位：秒
                .build();

        try {
            return minioClient.getPresignedObjectUrl(args);
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidResponseException | InvalidKeyException | NoSuchAlgorithmException | IOException | XmlParserException | ServerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取⽂件外链
     *
     * @param bucketName bucket名称
     * @param objectName ⽂件名称
     * @param duration 过期时间
     * @param unit  过期时间的单位
     */
    public String getObjectUrl(String bucketName, String objectName, int duration, TimeUnit unit) {
        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(bucketName)
                .object(objectName)
                .expiry(duration, unit)
                .build();

        try {
            return minioClient.getPresignedObjectUrl(args);
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidResponseException | InvalidKeyException | NoSuchAlgorithmException | IOException | XmlParserException | ServerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取文件
     *
     * @param bucketName bucket名称
     * @param objectName ⽂件名称
     * @return ⼆进制流
     */
    public InputStream getObject(String bucketName, String objectName) throws Exception {
        GetObjectArgs args = GetObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build();
        return minioClient.getObject(args);
    }

    /**
     * 上传文件
     *
     * @param bucketName bucket名称
     * @param objectName ⽂件名称
     * @param stream     ⽂件流
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    public void putObject(String bucketName, String objectName, InputStream stream) {
        putObjectByStream(bucketName, objectName, stream);
    }

    /**
     * 文件流上传文件
     *
     * @param bucketName  bucket名称
     * @param objectName  ⽂件名称
     * @param stream      ⽂件流
     * @param size        ⼤⼩
     * @param contextType 类型
     */
    public void putObject(String bucketName, String objectName, InputStream stream, long size, String contextType) {
        putObjectByStream(bucketName, objectName, stream);
    }

    /**
     * 获取文件信息
     *
     * @param bucketName bucket名称
     * @param objectName ⽂件名称
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#statObject
     */
    public StatObjectResponse getObjectInfo(String bucketName, String objectName) {
        StatObjectArgs args = StatObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build();
        try {
            return minioClient.statObject(args);
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除文件
     *
     * @param bucketName bucket名称
     * @param objectName ⽂件名称
     * @throws Exception https://docs.minio.io/cn/java-client-apireference.html#removeObject
     */
    public void removeObject(String bucketName, String objectName) {
        RemoveObjectArgs args = RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build();
        try {
            minioClient.removeObject(args);
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            e.printStackTrace();
        }
    }
}

