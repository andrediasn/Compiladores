
function main() {
    let i;
    let nlines;
    nlines = 5;
    for(let it15_3 = nlines; it15_3 > 0; it15_3--) {

        i = nlines;
        for(let it17_5 = i; it17_5 > 0; it17_5--) {

            process.stdout.write('*'.toString());
        }
        nlines = (nlines - 1);
        process.stdout.write('\n'.toString());
    }
}
main();  