const tableContainer = document.querySelector(".container");
const tableRow = tableContainer.querySelectorAll(".train-row");
const searchBar = document.getElementById("search-bar");
const output = document.querySelector(".search-bar-output");
const outputText = document.createElement("span");
output.appendChild(outputText);
tableRow.forEach(tableElement => {
    const destination = tableElement.querySelector(".train-destination");
    console.log(destination);
});

function missingTrains() {
    const trainRows = document.querySelectorAll(".train-row").forEach(row => {
        const trainId = row.querySelector(".train-id");
        if (trainId.textContent.trim() === '') {
            trainId.textContent = 'N/A';
        }
    })
}


function applyFilter() {
    const query = searchBar.value.trim().toLowerCase();
    let visible = 0;
    document.querySelectorAll(".train-row").forEach(row => {
        row.hidden = !row.textContent.toLowerCase().includes(query);
        if (!row.hidden) visible++;
    });
    outputText.textContent = `${visible} matching trains`;

}
searchBar.addEventListener("input", applyFilter);
missingTrains();