include infra.env

ifndef APPLICATION_NAME
$(error APPLICATION_NAME is not set)
endif
ifndef AWS_REGION
AWS_REGION := $(shell aws configure get region)
endif

GITHUB_REPO_BRANCH=$(shell git rev-parse --abbrev-ref HEAD)
export GITHUB_REPO_BRANCH
MASTER_STACK_NAME=$(APPLICATION_NAME)-master-stack
AWS_ACCOUNT_ID := $(shell aws sts get-caller-identity --query "Account" --output text)
S3_BUCKET_NAME=$(APPLICATION_NAME)-$(AWS_ACCOUNT_ID)-$(AWS_REGION)-bucket

all: s3-bucket
	mkdir -p packaged-templates
	echo "AWS_REGION $(AWS_REGION)"
	envsubst < cluster-template.yml  > packaged-templates/cluster-template.yml
	- eksctl create cluster -f  packaged-templates/cluster-template.yml
delete-all:
	$(MAKE) -j2 delete-s3-bucket
	eksctl delete cluster --name $(APPLICATION_NAME)


export S3_BUCKET_NAME
s3-bucket::
	aws cloudformation deploy    \
          --stack-name $(S3_BUCKET_NAME)   \
          --template-file ../s3-bucket/s3-bucket.yml   \
          --parameter-overrides     \
            BucketName=$(S3_BUCKET_NAME)
delete-s3-bucket:
	../stack-deletion/delete-stack-wait-termination.sh $(S3_BUCKET_NAME)
