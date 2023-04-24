function toggleRowClass(checkbox) {
    let row = checkbox.closest('tr');
    row.classList.toggle('light-row');
    row.classList.toggle('bold-row');
}
