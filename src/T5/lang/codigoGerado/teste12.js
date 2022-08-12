
function even( n) {
    let returns = [];
    if((n == 0)) {
        returns.push(true);
        return returns;
    } else {
        returns.push(odd((n - 1))[0]);
        return returns;
    }

}

function odd( n) {
    let returns = [];
    if((n == 0)) {
        returns.push(false);
        return returns;
    } else {
        returns.push(even((n - 1))[0]);
        return returns;
    }

}

function main() {
    let r;
    r = even(3)[0];
    if(r) {

        process.stdout.write('P'.toString());
        process.stdout.write('A'.toString());
        process.stdout.write('R'.toString());
    } else {

        process.stdout.write('I'.toString());
        process.stdout.write('M'.toString());
        process.stdout.write('P'.toString());
        process.stdout.write('A'.toString());
        process.stdout.write('R'.toString());
    }

}
main();  