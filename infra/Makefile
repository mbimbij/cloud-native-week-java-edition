ifndef APPLICATION_NAME
$(error APPLICATION_NAME is not set)
endif
ifndef AWS_REGION
AWS_REGION := $(shell aws configure get region)
endif

MASTER_STACK_NAME=$(APPLICATION_NAME)-master-stack

all: s3-bucket
delete-all:
	$(MAKE) delete-s3-bucket


AWS_ACCOUNT_ID := $(shell aws sts get-caller-identity --query "Account" --output text)
S3_BUCKET_NAME=$(APPLICATION_NAME)-$(AWS_ACCOUNT_ID)-$(AWS_REGION)-bucket
export S3_BUCKET_NAME
s3-bucket::
	aws cloudformation deploy    \
          --stack-name $(S3_BUCKET_NAME)   \
          --template-file s3-bucket/s3-bucket.yml   \
          --parameter-overrides     \
            BucketName=$(S3_BUCKET_NAME)
delete-s3-bucket:
	./stack-deletion/delete-stack-wait-termination.sh $(S3_BUCKET_NAME)
