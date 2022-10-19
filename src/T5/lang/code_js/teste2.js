
function main() {
    x = 0;
    process.stdout.write('>'.toString());
    input = require('prompt-sync')();
    x = input();
    process.stdout.write(x.toString());
    process.stdout.write('\n'.toString());
}

main();  