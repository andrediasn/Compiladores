
function fibonacci(n) {
    if((n < 1)) {
        return [n];
    } 
    if((n == 1)) {
        return [n];
    } 
    return [(fibonacci((n - 1))[0] + fibonacci((n - 2))[0])];
}

function main() {
    v = fibonacci(5)[0];
    process.stdout.write(v.toString());
}

main();  