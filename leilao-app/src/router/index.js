import Vue from 'vue'
import VueRouter from 'vue-router'
import Unidades from '../views/unidade/Unidades.vue'
import Empresas from '../views/empresa/Empresas.vue'
import FormEmpresa from '../views/empresa/FormEmpresa.vue'
import Leiloes from '../views/leiloes/Leiloes.vue'
import FormLeilao from '../views/leiloes/FormLeilao.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/unidades',
    name: 'Unidades',
    component: Unidades,
  },
  {
    name: 'Empresas',
    path: '/empresas',
    component: Empresas,
  },
  {
    name: 'cadastro-empresa',
    path: '/empresas/cadastro-empresa/:id?',
    component: FormEmpresa,
  },
  {
    name: 'leiloes',
    path: '/leiloes',
    component: Leiloes,
  },
  {
    name: 'cadastro-leilao',
    path: '/leiloes/cadastro-leilao/:id?',
    component: FormLeilao,
  }
]



const router = new VueRouter({
  routes
})

export default router
