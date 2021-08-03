import treeNav from 'vue-tree-nav';

<template>
 <nav>
    <div v-on:click="openMobileNav()" id="burger">
        <div class="line1"></div>
        <div class="line2"></div>
        <div class="line3"></div>
    </div>
    <div v-if="name" id="logo">
         {{ name }}
    </div>
    <div v-else id="logo">
        <img v-bind:src="logoImg" alt="Logo" />
    </div>
    <ul class="nav-links">
        <li v-for="list in navLinks" :key="list.key">
            <a v-if="list.dropdown === false" :href="list.link">
                {{ list.name }}
            </a>
            <div class="dropdown-link" v-else>
                <a :href="list.link">
                    {{ list.name }}
                </a>
                
            </div>
        </li>
    </ul>
 </nav>
</template>

<script>
export default {

    name: 'Navbar',
props: ['name', 'logoImg', 'navLinks'],
methods: {
    openMobileNav() {
    const burger = document.getElementById('burger')
    const nav = document.querySelector('.nav-links')
    const navLinks = document.querySelectorAll('.nav-links li')
// Toggle navigation on mobile
    nav.classList.toggle('nav-active')
    // Burger toggler
    burger.classList.toggle('toggle')
// Animate navigation links
    navLinks.forEach((link, index) => {
        if (link.style.animation || link.style.webkitAnimation) {
            link.style.animation = ''
            link.style.webkitAnimation = ''
        } else {
            link.style.webkitAnimation = `navLinkFade 0.5s ease forwards ${
            index / 7
          }s`
            link.style.animation = `navLinkFade 0.5s ease forwards ${index / 7}s`
        }
        })
    }       
    }
}

</script>

<style>

body {
 margin: 0px;
}


nav {
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
    background-color: #DDA0DD;
    min-height: 8vh;
    font-family: 'Montserrat', sans-serif;
    position: fixed;
    z-index: 2;
}

@media only screen and (max-width: 3440px) {

nav {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    background-color: #0a020a;
    min-height: 8vh;
    font-family: 'Montserrat', sans-serif;
    position: relative;
    z-index: 2;
}
  ul.nav-links {
    display: flex;
    flex-direction: row;
    width: 50%;
    height: 5%;
    top: 3vh;
    /* right: 105%; */
    /* padding: 100px; */
    justify-content: space-evenly;
    /* background-color: #DDA0DD; */
    opacity: 0.9;
    z-index: 4;
  }
  ul.nav-links li {
    justify-content: flex-end;
    display: flex;
    opacity: 1;
    /* margin: 0 35px; */
    margin-left: 30px;

  }
  ul.nav-links a {
    display: flex;
    /* margin: 10px; */
  }
  
  div#logo {
    display: flex;
    flex-basis: 25%;
    /* width: 15%; */
    justify-content: flex-start;
    letter-spacing: 10px;
    color: #fefefe;
    font-weight: 800;
    font-size: 3rem;
    z-index: 2;
}

}
@media  only screen and (max-width: 767px) {
  ul.nav-links {
    position: absolute;
    flex-direction: column;
    width: 20%;
    height: 100vh;
    top: -40px;
    right: 105%;
    padding: 100px;
    align-items: center;
    justify-content: flex-start;
    background-color: #DDA0DD;
    opacity: .95;
    transform: translateX(-100%);
    transition: transform 0.5s ease-in;
    z-index: 1;
  }
  ul.nav-links li {
    height: 60px;
    justify-content: flex-end;
    display: flex;
    opacity: 1;
    align-items: center;
    
  }

  
  ul.nav-links a {
    text-decoration: none;
    color: #fefefe;
    font-size: 1.2rem;
    font-weight: 500;
    display: flex;
  }
  div#burger {
    display: block;
    width: 40%;
    padding: 25px;
    position: relative;
    z-index: 2;
  }

 #burger div {
  display: flex;
  width: 30px;
  height: 3px;
  margin: 8px;
  background-color: #fefefe;
  transition: all 0.3s ease-in;
}

  #burger {
  display: flex;
  cursor: pointer;
}
  div#logo {
    position: relative;
    width: 100%;
    align-content: center;
    letter-spacing: 10px;
    color: #fefefe;
    font-weight: 800;
    font-size: 2rem;
    z-index: 2;

  }
  

  .nav-active {
  transform: translateX(75%) !important;
  
  
}
.toggle .line1 {
  transform: rotate(-45deg) translate(-9px, 10px);
}
.toggle .line2 {
  opacity: 0;
}
.toggle .line3 {
  transform: rotate(45deg) translate(-5px, -6px);
}
@keyframes navLinkFade {
  from {
    opacity: 0;
    transform: translateX(-60px);
  }
to {
    opacity: 1;
    transform: translateX(0px);
  }
}

  body {
 margin: -8px;
}


}

@media  only screen and (max-width: 330px) {
  ul.nav-links {
    position: absolute;
    flex-direction: column;
    width: 25%;
    height: 100vh;
    top: -50px;
    right: 105%;
    padding: 100px;
    align-items: center;
    justify-content: flex-stacrt;
    background-color: #DDA0DD;
    opacity: .9;
    transform: translateX(-100%);
    transition: transform 0.5s ease-in;
    z-index: 1;
  }
  ul.nav-links li {
    display: flex;
    justify-content: flex-end;
    opacity: 1;
    height: 60px; 
    align-items: center;
  }
  ul.nav-links a {
    display: block;
    width: 100%;
  }
  div#burger {
    display: block;
    z-index: 2;
  }
  div#logo {
    width: 150%;
    align-content: center;
    letter-spacing: 10px;
    color: #fefefe;
    font-weight: 800;
    font-size: 2rem;
    z-index: 2;

  }

  .nav-active {
  transform: translateX(75%) !important;
  
  
}
.toggle .line1 {
  transform: rotate(-45deg) translate(-9px, 10px);
}
.toggle .line2 {
  opacity: 0;
}
.toggle .line3 {
  transform: rotate(45deg) translate(-5px, -6px);
}
@keyframes navLinkFade {
  from {
    opacity: 0;
    transform: translateX(-60px);
  }
to {
    opacity: 1;
    transform: translateX(0px);
  }
}
}


</style>