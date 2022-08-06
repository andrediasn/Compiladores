
main();

function fibonacci( n) {
    let returns = [];
    if((n < 1)) {
        returns.push(n);
        return returns;
    } 
    if((n == 1)) {
        returns.push(n);
        return returns;
    } 
    returns.push((((let)fibonacci(String.valueOf(0), (n - 1))) + ((let)fibonacci(String.valueOf(0), (n - 2)))));
    return returns;
}

function main() {
    let v = 0;
    v = ((let)fibonacci(String.valueOf(0), 5));
    process.stdout.write(v.toString());
}
  