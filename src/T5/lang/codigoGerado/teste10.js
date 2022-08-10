
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
    returns.push((fibonacci((n - 1))[0] + fibonacci((n - 2))[0]));
    return returns;
}

function main() {
    let v = 0;
    v = fibonacci(5)[0];
    process.stdout.write(v.toString());
}
  