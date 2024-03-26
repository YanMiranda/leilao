export default {
  data() {
    return {
      widthMobile: window.innerWidth || 0,
    };
  },

  mounted() {
    window.addEventListener('resize', () => {
      this.widthMobile = window.innerWidth;
    });
  },

  computed: {
    isMobile() {
      if (this.widthMobile < 768) {
        return true;
      }
      return false;
    },
  },
  methods: {
    modalResponsive(width = 60) {
      if (this.isMobile) {
        return '90%';
      }
      return `${width}%`;
    },
    menuLeftResponsive(width = 80) {
      if (this.isMobile) {
        return '80%';
      }
      return `${width}%`;
    },
  },
};