package com.example.cloudnativeweekjavaedition;

import org.junit.jupiter.api.Test;

public class PlopTest {
    @Test
    void name() {
        System.out.println("{\"allowed\":false,\"explicitDeny\":false,\"matchedStatements\":{\"items\":[]},\"failures\":{\"items\":[]},\"context\":{\"principal\":{\"id\":\"AROAZK6WDJGGPYAVRUAKL:i-05f3bedd4bce35744\",\"arn\":\"arn:aws:sts::642009745804:assumed-role/cloud-native-java-master-stack-myAmazonEKSNodeRole-1ASUBBQHW74ZD/i-05f3bedd4bce35744\"},\"action\":\"ec2:ModifyNetworkInterfaceAttribute\",\"resource\":\"arn:aws:ec2:eu-west-3:642009745804:network-interface/eni-08ee7a84bf060a66a\",\"conditions\":{\"items\":[{\"key\":\"ec2:Vpc\",\"values\":{\"items\":[{\"value\":\"arn:aws:ec2:eu-west-3:642009745804:vpc/vpc-05513cacbbea829b2\"}]}},{\"key\":\"ec2:NetworkInterfaceID\",\"values\":{\"items\":[{\"value\":\"eni-08ee7a84bf060a66a\"}]}},{\"key\":\"aws:Resource\",\"values\":{\"items\":[{\"value\":\"network-interface/eni-08ee7a84bf060a66a\"}]}},{\"key\":\"aws:Account\",\"values\":{\"items\":[{\"value\":\"642009745804\"}]}},{\"key\":\"ec2:AvailabilityZone\",\"values\":{\"items\":[{\"value\":\"eu-west-3b\"}]}},{\"key\":\"ec2:Attribute/Groups\",\"values\":{\"items\":[{\"value\":\"[\\\"sg-0667ca1f6359edef4\\\"]\"}]}},{\"key\":\"aws:Region\",\"values\":{\"items\":[{\"value\":\"eu-west-3\"}]}},{\"key\":\"aws:Service\",\"values\":{\"items\":[{\"value\":\"ec2\"}]}},{\"key\":\"ec2:Subnet\",\"values\":{\"items\":[{\"value\":\"arn:aws:ec2:eu-west-3:642009745804:subnet/subnet-0a6a04aa49fbb434f\"}]}},{\"key\":\"aws:Type\",\"values\":{\"items\":[{\"value\":\"network-interface\"}]}},{\"key\":\"ec2:Attribute\",\"values\":{\"items\":[{\"value\":\"Groups\"}]}},{\"key\":\"ec2:Region\",\"values\":{\"items\":[{\"value\":\"eu-west-3\"}]}},{\"key\":\"aws:ARN\",\"values\":{\"items\":[{\"value\":\"arn:aws:ec2:eu-west-3:642009745804:network-interface/eni-08ee7a84bf060a66a\"}]}}]}}}");
    }
}
