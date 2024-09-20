document.addEventListener('DOMContentLoaded', function() {
    console.log("Initializing chart...");
    var ctx = document.getElementById('genderChart');
    if (ctx) {
        console.log("Canvas found, rendering chart...");
        var genderChart = new Chart(ctx, {
            type: 'pie',
            data: {
                labels: ['Homme', 'Femme'],
                datasets: [{
                    data: [60, 40],
                    backgroundColor: ['#36A2EB', '#FF6384']
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: true,
            }
        });
    } else {
        console.error("Canvas not found.");
    }
});
