<template>
  <div>
    <TabelaDinamica
      :title="'Empresas'"
      :headers="fields"
      :items="empresas"
      :showBtnAdd="true"
      :showbtnColumn="true"
      :showSeachButton="true"
      :pagination="pagination"
      @onDelete="onDelete"
      @onSearch="onSearch"
      @onEdit="onEdit"
      @onAdd="onAdd"
      @fetchData="fetchEmpresas"
    />

    <BuscarEmpresa
      v-if="showDialog"
      ref="BuscarEmpresa"
      :showDialog="showDialog"
      @onSearch="fetchEmpresas"
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
import BuscarEmpresa from './BuscarEmpresa.vue';
import DialogDelete from '@/components/table/DeleteDialog.vue';

export default {
  name: 'Empresas',

  components: {
    TabelaDinamica,
    BuscarEmpresa,
    DialogDelete,
  },

  data() {
    return {
      showDialog: false,
      dialogDelete: false,
      selected: null,
      empresas: [],
      fields: [
        {
          value: 'cnpj',
          text: 'CNPJ',
          align: 'center',
        },
        {
          value: 'razaoSocial',
          text: 'Nome',
          align: 'center',
        },
        {
          value: 'telefone',
          text: 'Telefone',
          align: 'center',
        },
        {
          value: 'email',
          text: 'Email',
          align: 'center',
        },
        {
          value: 'actions',
          text: 'Ações',
        },
      ],
      pagination: {
        sortBy: 'codigo',
        page: 1,
        total: 0,
        pageCount: 0,
        itemsPerPage: 10,
      }
    };
  },

  created() {
    this.fetchEmpresas();
  },

  methods: {
    fetchEmpresas() {
      this.$axios.get('/empresas/filtro', {
        params: {
          ...this.$route.query,
          page: this.pagination.page - 1,
          size: this.pagination.itemsPerPage,
          sort: this.pagination.sortBy,
        }
      })
      .then((response) => {
        this.empresas = response.data.content;
        this.pagination.total = response.data.totalElements;
        this.pagination.pageCount = response.data.totalPages;
      });
    },

    onEdit(item) {
      this.$router.push({ path: `/empresas/cadastro-empresa/${item.id}` });
    },

    onDelete(item) {
      this.selected = item;
      this.dialogDelete = true;
    },

    onDeleteItemConfirm() {
      this.$axios.delete(`/empresas/${this.selected.id}`)
        .then(() => {
          this.dialogDelete = false;
          this.fetchEmpresas();
        });
    },

    onAdd() {
      this.$router.push({ name: 'cadastro-empresa' });
    },

    onSearch() {
      this.showDialog = true;
    },
  },
};
</script>

<style>

</style>
