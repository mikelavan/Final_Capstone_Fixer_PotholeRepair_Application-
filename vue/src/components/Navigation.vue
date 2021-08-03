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
                <ul class="dropdown-menu">
                 <li v-for="item in list.dropdownLinks" :key="item.key">
                     <a :href="item.link">
                         {{ item.name }}
                    </a>
                 </li>
                </ul>
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


nav {
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
    background-color: #fe8888;
    min-height: 8vh;
    font-family: 'Montserrat', sans-serif;
    position: relative;
    z-index: 2;
}

div#logo {
letter-spacing: 5px;
color: #fefefe;
font-weight: 800;
font-size: 2rem;

}

#burger {
display: flex;
cursor: pointer;
}

#burger div {
display: flex;
width: 30px;
height: 3px;
margin: 8px;
background-color: #fefefe;
transition: all 0.3s ease-in;

}


ul.nav-links {
display: flex;
justify-content: space-between;
width: 40%;
line-height: 75px;
}

ul.nav-links li {
list-style: none;

}

ul.nav-links a {
display: block;
text-decoration: none;
color: #fefefe;
font-size: 1.2rem;
font-weight: 500;
display: flex;
}

@media only screen and (max-width:  1400px) {
  ul.nav-links {
    position: absolute;
    flex-direction: column;
    width: 25%;
    height: auto;
    top: 6vh;
    right: 105%;
    padding: 100px;
    align-items: center;
    justify-content: flex-start;
    background-color: #fe8888;
    opacity: 0.9;
    transform: translateX(-100%);
    transition: transform 0.5s ease-in;
    z-index: 0;
  }
  ul.nav-links li {
    opacity: 0;
  }
  ul.nav-links a {
    width: 100%;
    margin: 20px;
  }
  div#burger {
    display: block;
    width: 40%;
    padding: 25px;
    position: relative;
    z-index: 1;
  }
  div#logo {
    width: 100%;
    letter-spacing: 55px;
    color: #fefefe;
    font-weight: 800;
    font-size: 4rem;
    z-index: 2;
}

}
@media  only screen and (max-width: 767px) {
  ul.nav-links {
     position: absolute;
    flex-direction: column;
    width: 25%;
    height: auto;
    top: -50px;
    right: 105%;
    padding: 100px;
    align-items: center;
    justify-content: flex-start;
    background-color: #fe8888;
    opacity: .95;
    transform: translateX(-100%);
    transition: transform 0.5s ease-in;
    z-index: 0;
  }
  ul.nav-links li {
    opacity: 0;
    height: 60px;
    
  }
  ul.nav-links a {
    width: 100%;
  }
  div#burger {
    display: block;
    width: 50;
    position: relative;
    z-index: 1;
  }
  div#logo {
    position: relative;
    width: 140%;
    align-content: center;
    letter-spacing: 15px;
    color: #fefefe;
    font-weight: 800;
    font-size: 2rem;
    z-index: 2;

  }
}

@media  only screen and (max-width: 330px) {
  ul.nav-links {
    position: absolute;
    flex-direction: column;
    width: 25%;
    height: auto;
    top: -50px;
    right: 105%;
    padding: 100px;
    align-items: center;
    justify-content: flex-start;
    background-color: #fe8888;
    opacity: .9;
    transform: translateX(-100%);
    transition: transform 0.5s ease-in;
    z-index: 0;
  }
  ul.nav-links li {
    opacity: 0;
    height: 60px; 
    width: 150%; 
  }
  ul.nav-links a {
    width: 100%;
  }
  div#burger {
    display: block;
    z-index: 1;
  }
  div#logo {
    width: 100%;
    align-content: center;
    letter-spacing: 15px;
    color: #fefefe;
    font-weight: 800;
    font-size: 2rem;
    z-index: 2;

  }
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


</style>