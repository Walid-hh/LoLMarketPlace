/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ['src/main/resources/templates/*.html'],
  theme: {
    extend: {
      fontFamily: {
        'lig1': ['Lig1', 'sans-serif'],
        'lig2': ['Lig2', 'sans-serif'],
      },
      colors: {
        'lig1': '#091428',
        'lig2': '#C89B3C',
        'lig3': '#0A323C',
        'lig11':'#005A82',
        'lig21':'#C8AA6E',
        'ligblue1':'#0397AB',
      },
      backgroundImage: {
        'img1':"url('images/login-bg.png')",
        'img2':"url('images/skin.jpg')",
        'bg-img':"url('images/lig-bg.jpg')",
      }
    },
  },
  plugins: [],
}

