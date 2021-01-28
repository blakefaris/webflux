echo "------------------------------------------------"
echo "Running load test"
echo "------------------------------------------------"
docker run --add-host=host.docker.internal:host-gateway -v "$(pwd)"/src/test/k6:/scripts -i loadimpact/k6 run /scripts/script.js