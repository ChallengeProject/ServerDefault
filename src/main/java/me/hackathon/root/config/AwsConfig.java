package me.hackathon.root.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsConfig {
    private String accessKey = "AKIAI7VBNUOJGTJ47BJQ";
    private String secretKey = "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";

    @Bean
    public AmazonS3 amazonS3() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey,
                                                             secretKey);

        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTP);
        clientConfig.setSignerOverride("S3SignerType");

        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                                           .withCredentials(new AWSStaticCredentialsProvider(credentials))
                                           .withRegion(Regions.AP_NORTHEAST_2)
                                           .build();

        return s3;
    }
}
