/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {
      backgroundImage: {
        'custom-gradient': 'linear-gradient(315deg, #065f46 0%, #6ee7b7 74%)',
      },
      backgroundColor: {
        'custom-green': '#065f46',
      },
    },
  },
  plugins: [],
}