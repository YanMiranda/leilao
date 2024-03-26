<template>
  <div>
    <TabelaDinamica
      title="Leilões"
      :headers="headers"
      :items="leiloes"
      :showBtnAdd="true"
      :showbtnColumn="true"
      :showSeachButton="true"
      :pagination="pagination"
      @onAdd="onClickAdd"
      @onSearch="onSearch"
      @onEdit="onClickEdit"
      @onDelete="onClickDelete"
      @fetchData="fetchLeiloes"
    />

    <BuscarLeilao
      v-if="showDialog"
      ref="BuscarLeilao"
      :showDialog="showDialog"
      @onSearch="fetchLeiloes"
      @closeDialog="showDialog = false"
    />

    <DialogDelete
      :dialogDelete="dialogDelete"
      @onDeleteItem="onDeleteItemConfirm"
      @onCancelDelete="dialogDelete = false"
    />
  </div>
</template>

<script>
import TabelaDinamica from '@/components/table/TabelaDinamica.vue';
import BuscarLeilao from './BuscarLeilao.vue';
import DialogDelete from '@/components/table/DeleteDialog.vue';
import { formatdate } from '@/util/DateUtil.js';

export default {
  name: 'Leiloes',

  components: {
    TabelaDinamica,
    BuscarLeilao,
    DialogDelete,
  },

  data() {
    return{
      formatdate,
      showDialog: false,
      dialogDelete: false,
      selected: null,
      leiloes: [],
      headers: [
        {
          value: 'codigo',
          text: 'Código',
        },
        {
          value: 'vendedor',
          text: 'Vendedor',
        },
        {
          value: 'inicioPrevisto',
          text: 'Início Previsto',
        },
        {
          value: 'descricao',
          text: 'Descrição',
        },
        {
          value: 'actions',
          text: 'Ações',
        }
      ],
      pagination: {
        sortBy: 'codigo',
        page: 1,
        total: 0,
        pageCount: 0,
        itemsPerPage: 10,
      }
    }
  },

  created() {
    this.fetchLeiloes();
  },

  methods: {
    fetchLeiloes() {
      this.$axios.get('/leiloes/filtro', {
        params: {
          ...this.$route.query,
          page: this.pagination.page - 1,
          size: this.pagination.itemsPerPage,
          sort: this.pagination.sortBy,
        }
      })
      .then(response => {
        this.leiloes = response.data.content;
        this.formataData();
        this.pagination.total = response.data.totalElements;
        this.pagination.pageCount = response.data.totalPages;
      })
    },

    formataData() {
      this.leiloes.forEach(leilao => {
        leilao.inicioPrevisto = this.formatdate(leilao.inicioPrevisto);
      });
    },

    onClickAdd() {
      this.$router.push({ name: 'cadastro-leilao'});
    },

    onClickEdit(item) {
      this.$router.push({ path: `/leiloes/cadastro-leilao/${item.id}` });
    },

    onClickDelete(item) {
      this.selected = item;
      this.dialogDelete = true;
    },

    onDeleteItemConfirm() {
      this.$axios.delete(`/leiloes/${this.selected.id}`)
        .then(() => {
          this.dialogDelete = false;
          this.fetchLeiloes();
        })
    },

    onSearch() {
      this.showDialog = true;
    },
  },
}
</script>

<style>

</style>