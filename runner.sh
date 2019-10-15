python3 PythonEncoder.py

docker build -t scala_environment .
docker run \
    -it \
    --rm \
    --volume $(pwd)/puzzle_unsolved.txt:/app/puzzle_unsolved.txt \
    --volume $(pwd)/puzzle_unsolved.bin:/app/puzzle_unsolved.bin \
    --volume $(pwd)/puzzle_solved.bin:/app/puzzle_solved.bin \
    scala_environment /bin/bash -c "sbt run && exit" 

python3 PythonDecoder.py
