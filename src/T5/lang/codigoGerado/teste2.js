
function main() {
    x = 0;
    process.stdout.write('>'.toString());
    x = process.stdin.read();
    process.stdout.write(x.toString());
    process.stdout.write('\n'.toString());
}

main();  