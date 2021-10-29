kind-cluster:
	kind create cluster
	k config use-context kind-kind

helm-repos:
	helm repo add bitnami https://charts.bitnami.com/bitnami

efk:
	set -x
	./install.sh

delete-all:
	kind delete cluster