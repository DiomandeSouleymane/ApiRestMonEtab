const pieCanvas = document.getElementById('pieCanvas').getContext('2d');
const genderChart = new Chart(pieCanvas, {
    type: 'pie',
    data: {
        labels: ['Homme', 'Femme'],
        datasets: [{
            data: [60, 40],
            backgroundColor: ['#FF6384', '#36A2EB'],
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
            legend: {
                display: true,
                position: 'right',
            }
        }
    }
});

console.log("OK");